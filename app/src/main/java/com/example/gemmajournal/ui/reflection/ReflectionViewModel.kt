package com.example.gemmajournal.ui.reflection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gemmajournal.data.JournalEntry
import com.example.gemmajournal.data.JournalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ReflectionUiState(
    val entry: JournalEntry? = null,
    val isEditing: Boolean = false,
    val editedTags: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ReflectionViewModel(
    private val repository: JournalRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ReflectionUiState())
    val uiState: StateFlow<ReflectionUiState> = _uiState.asStateFlow()
    
    fun setEntry(entry: JournalEntry) {
        _uiState.value = ReflectionUiState(
            entry = entry,
            editedTags = entry.tags
        )
    }
    
    fun toggleEditing() {
        _uiState.value = _uiState.value.copy(isEditing = !_uiState.value.isEditing)
    }
    
    fun updateTags(tags: List<String>) {
        _uiState.value = _uiState.value.copy(editedTags = tags)
    }
    
    fun saveChanges(onSuccess: () -> Unit) {
        val entry = _uiState.value.entry ?: return
        
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                val updatedEntry = entry.copy(
                    tags = _uiState.value.editedTags,
                    updatedAt = java.time.LocalDateTime.now()
                )
                
                repository.updateEntry(updatedEntry)
                
                _uiState.value = _uiState.value.copy(
                    entry = updatedEntry,
                    isEditing = false,
                    isLoading = false
                )
                
                onSuccess()
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Failed to save changes: ${e.message}"
                )
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
} 