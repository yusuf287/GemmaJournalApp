package com.example.gemmajournal.ui.newentry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState

@Composable
fun NewEntryScreen(
    viewModel: NewEntryViewModel,
    onNavigateToReflection: (com.example.gemmajournal.data.JournalEntry) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "What's on your heart today?",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = uiState.content,
            onValueChange = { viewModel.updateContent(it) },
            label = { Text("Your thoughts") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            placeholder = { Text("Type or speak your thoughts...") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            ),
            maxLines = 10,
            enabled = !uiState.isLoading
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Voice recording button (placeholder for future implementation)
        IconButton(
            onClick = { /* TODO: Implement voice recording with whisper.cpp */ },
            modifier = Modifier.padding(8.dp),
            enabled = !uiState.isLoading
        ) {
            Icon(
                imageVector = Icons.Filled.Mic,
                contentDescription = "Record voice entry"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.submitEntry(onNavigateToReflection) },
            enabled = uiState.content.isNotBlank() && !uiState.isLoading,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Submit journal entry"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(if (uiState.isLoading) "Processing..." else "Submit")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { 
                onNavigateToReflection(
                    com.example.gemmajournal.data.JournalEntry(
                        content = "Not in the mood today",
                        reflection = "Sometimes it's okay to not feel like journaling",
                        mood = "Neutral",
                        tags = listOf("Skip", "Not in mood")
                    )
                ) 
            },
            enabled = !uiState.isLoading
        ) {
            Text("Not in the mood?")
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

@Preview(showBackground = true)
@Composable
fun NewEntryScreenPreview() {
    MaterialTheme {
        // Preview placeholder - would need actual ViewModel instance
        Text("New Entry Screen Preview")
    }
}
