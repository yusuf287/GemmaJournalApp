package com.example.gemmajournal

import android.app.Application
import com.example.gemmajournal.data.JournalDatabase

class GemmaJournalApplication : Application() {
    
    val database: JournalDatabase by lazy { JournalDatabase.getDatabase(this) }
    
    override fun onCreate() {
        super.onCreate()
        // Initialize any app-wide dependencies here
    }
} 