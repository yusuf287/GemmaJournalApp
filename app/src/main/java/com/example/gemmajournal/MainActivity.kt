package com.example.gemmajournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.gemmajournal.data.JournalDatabase
import com.example.gemmajournal.navigation.GemmaJournalNavigation
import com.example.gemmajournal.ui.theme.GemmaJournalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GemmaJournalTheme {
                GemmaJournalApp()
            }
        }
    }
}

@Composable
fun GemmaJournalApp() {
    val database = JournalDatabase.getDatabase(LocalContext.current)
    val navController = rememberNavController()
    
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        GemmaJournalNavigation(
            navController = navController,
            database = database,
            modifier = Modifier.padding(innerPadding)
        )
    }
}