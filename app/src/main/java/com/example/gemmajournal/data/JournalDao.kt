package com.example.gemmajournal.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao {
    @Query("SELECT * FROM journal_entries ORDER BY createdAt DESC")
    fun getAllEntries(): Flow<List<JournalEntry>>

    @Query("SELECT * FROM journal_entries WHERE id = :id")
    suspend fun getEntryById(id: Long): JournalEntry?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: JournalEntry): Long

    @Update
    suspend fun updateEntry(entry: JournalEntry)

    @Delete
    suspend fun deleteEntry(entry: JournalEntry)

    @Query("SELECT * FROM journal_entries WHERE content LIKE '%' || :query || '%' OR reflection LIKE '%' || :query || '%' OR mood LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    suspend fun searchEntries(query: String): List<JournalEntry>

    @Query("SELECT * FROM journal_entries WHERE mood LIKE '%' || :mood || '%' ORDER BY createdAt DESC")
    suspend fun getEntriesByMood(mood: String): List<JournalEntry>

    @Query("SELECT * FROM journal_entries WHERE tags LIKE '%' || :tag || '%' ORDER BY createdAt DESC")
    suspend fun getEntriesByTag(tag: String): List<JournalEntry>
} 