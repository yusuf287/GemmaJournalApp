package com.example.gemmajournal.ui.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gemmajournal.data.JournalEntry
import com.example.gemmajournal.data.JournalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TimelineUiState(
    val entries: List<JournalEntry> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedEntry: JournalEntry? = null,
    val expandedEntries: Set<Long> = emptySet()
)

class TimelineViewModel(
    private val repository: JournalRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(TimelineUiState())
    val uiState: StateFlow<TimelineUiState> = _uiState.asStateFlow()
    
    init {
        loadEntries()
    }
    
    private fun loadEntries() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                val entries = repository.getAllEntriesList()
                _uiState.value = _uiState.value.copy(
                    entries = entries,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Failed to load entries: ${e.message}"
                )
            }
        }
    }
    
    fun toggleEntryExpansion(entryId: Long) {
        val currentExpanded = _uiState.value.expandedEntries.toMutableSet()
        if (currentExpanded.contains(entryId)) {
            currentExpanded.remove(entryId)
        } else {
            currentExpanded.add(entryId)
        }
        _uiState.value = _uiState.value.copy(expandedEntries = currentExpanded)
    }
    
    fun deleteEntry(entry: JournalEntry) {
        viewModelScope.launch {
            try {
                repository.deleteEntry(entry)
                // Entries will be automatically updated through Flow
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Failed to delete entry: ${e.message}"
                )
            }
        }
    }
    
    fun setSelectedEntry(entry: JournalEntry?) {
        _uiState.value = _uiState.value.copy(selectedEntry = entry)
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
} 