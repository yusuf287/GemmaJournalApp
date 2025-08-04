package com.example.gemmajournal.ui.reflection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import com.example.gemmajournal.data.JournalEntry

@Composable
fun ReflectionScreen(
    entry: JournalEntry,
    viewModel: ReflectionViewModel,
    onNavigateToNewEntry: () -> Unit,
    onNavigateToMemoryRecall: () -> Unit,
    onNavigateToTimeline: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(entry) {
        viewModel.setEntry(entry)
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "What did Gemma understand from your entry?",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Reflection Summary Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Reflection",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = entry.reflection,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Mood and Tags Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mood & Tags",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    IconButton(onClick = { viewModel.toggleEditing() }) {
                        Icon(
                            imageVector = if (uiState.isEditing) Icons.Filled.Check else Icons.Filled.Edit,
                            contentDescription = if (uiState.isEditing) "Save changes" else "Edit tags"
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Mood
                Text(
                    text = "Mood: ${entry.mood}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                // Tags
                if (uiState.editedTags.isNotEmpty()) {
                    Text(
                        text = "Tags:",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(vertical = 4.dp)
                    ) {
                        items(uiState.editedTags) { tag ->
                            AssistChip(
                                onClick = { /* Tag click handling */ },
                                label = { Text(tag) }
                            )
                        }
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (uiState.isEditing) {
                        viewModel.saveChanges { /* Success callback */ }
                    } else {
                        onNavigateToTimeline()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = if (uiState.isEditing) Icons.Filled.Save else Icons.Filled.List,
                    contentDescription = if (uiState.isEditing) "Save" else "View Timeline"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (uiState.isEditing) "Save" else "View Timeline")
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Button(
                onClick = onNavigateToMemoryRecall,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Ask About Past"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Ask About Past")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = onNavigateToNewEntry,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "New Entry"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("New Entry")
        }
        
        if (uiState.isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()
        }
        
        uiState.error?.let { error ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

 