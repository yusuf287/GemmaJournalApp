package com.example.gemmajournal.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class JournalRepository(private val journalDao: JournalDao) {
    
    fun getAllEntries(): Flow<List<JournalEntry>> = journalDao.getAllEntries()
    
    suspend fun getAllEntriesList(): List<JournalEntry> {
        return journalDao.getAllEntries().first()
    }
    
    suspend fun getEntryById(id: Long): JournalEntry? = journalDao.getEntryById(id)
    
    suspend fun insertEntry(entry: JournalEntry): Long = journalDao.insertEntry(entry)
    
    suspend fun updateEntry(entry: JournalEntry) = journalDao.updateEntry(entry)
    
    suspend fun deleteEntry(entry: JournalEntry) = journalDao.deleteEntry(entry)
    
    suspend fun searchEntries(query: String): List<JournalEntry> = journalDao.searchEntries(query)
    
    suspend fun getEntriesByMood(mood: String): List<JournalEntry> = journalDao.getEntriesByMood(mood)
    
    suspend fun getEntriesByTag(tag: String): List<JournalEntry> = journalDao.getEntriesByTag(tag)
} 