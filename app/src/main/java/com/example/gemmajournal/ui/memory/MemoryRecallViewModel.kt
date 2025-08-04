package com.example.gemmajournal.ui.memory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gemmajournal.ai.AIService
import com.example.gemmajournal.data.JournalEntry
import com.example.gemmajournal.data.JournalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MemoryRecallUiState(
    val query: String = "",
    val searchResult: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val allEntries: List<JournalEntry> = emptyList()
)

class MemoryRecallViewModel(
    private val repository: JournalRepository,
    private val aiService: AIService
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(MemoryRecallUiState())
    val uiState: StateFlow<MemoryRecallUiState> = _uiState.asStateFlow()
    
    init {
        loadAllEntries()
    }
    
    private fun loadAllEntries() {
        viewModelScope.launch {
            try {
                val entries = repository.getAllEntriesList()
                _uiState.value = _uiState.value.copy(allEntries = entries)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = "Failed to load entries: ${e.message}")
            }
        }
    }
    
    fun updateQuery(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }
    
    fun searchMemories() {
        if (_uiState.value.query.isBlank()) return
        
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                val result = aiService.searchMemories(_uiState.value.query, _uiState.value.allEntries)
                _uiState.value = _uiState.value.copy(
                    searchResult = result,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Failed to search memories: ${e.message}"
                )
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
    
    fun clearSearch() {
        _uiState.value = _uiState.value.copy(
            query = "",
            searchResult = ""
        )
    }
} 