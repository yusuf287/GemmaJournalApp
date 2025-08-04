package com.example.gemmajournal.ui.newentry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send // Added for the Send icon
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NewEntryScreen(
    onNavigateToReflection: () -> Unit
) {
    var journalText by remember { mutableStateOf("") }
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
            style = MaterialTheme.typography.headlineMedium, // Changed to headlineMedium
            color = MaterialTheme.colorScheme.primary, // Added primary color
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = journalText,
            onValueChange = { journalText = it },
            label = { Text("Your thoughts") }, // Added label
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            placeholder = { Text("Type or speak your thoughts...") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            ),
            maxLines = 10
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Consider revisiting the purpose of this IconButton and its icon
        IconButton(
            onClick = { /* TODO: Implement action (e.g., voice recording, attachment) */ },
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add attachment or use voice" // Updated C.D.
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* TODO: Trigger LLM reflection */ },
            enabled = journalText.isNotBlank(),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "Submit journal entry"
            )
            Spacer(modifier = Modifier.width(8.dp)) // Added spacer for icon-text
            Text("Submit")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onNavigateToReflection) {
            Text("Not in the mood?")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewEntryScreenPreview() {
    MaterialTheme {
        NewEntryScreen(onNavigateToReflection = {})
    }
}
