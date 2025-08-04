package com.example.gemmajournal.ui.newentry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gemmajournal.ai.AIService
import com.example.gemmajournal.data.JournalEntry
import com.example.gemmajournal.data.JournalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class NewEntryUiState(
    val content: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class NewEntryViewModel(
    private val repository: JournalRepository,
    private val aiService: AIService
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(NewEntryUiState())
    val uiState: StateFlow<NewEntryUiState> = _uiState.asStateFlow()
    
    fun updateContent(content: String) {
        _uiState.value = _uiState.value.copy(content = content)
    }
    
    fun submitEntry(onSuccess: (JournalEntry) -> Unit) {
        if (_uiState.value.content.isBlank()) return
        
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                // Generate AI reflection
                val aiResponse = aiService.generateReflection(_uiState.value.content)
                
                // Create journal entry
                val entry = JournalEntry(
                    content = _uiState.value.content,
                    reflection = aiResponse.reflection,
                    mood = aiResponse.mood,
                    tags = aiResponse.tags
                )
                
                // Save to database
                val entryId = repository.insertEntry(entry)
                val savedEntry = entry.copy(id = entryId)
                
                // Reset UI state
                _uiState.value = NewEntryUiState()
                
                // Call success callback
                onSuccess(savedEntry)
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Failed to process entry: ${e.message}"
                )
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
} 