package com.example.gemmajournal

import com.example.gemmajournal.data.JournalEntry
import com.example.gemmajournal.ai.AIService
import org.junit.Test
import org.junit.Assert.*
import kotlinx.coroutines.runBlocking

class DataModelTest {
    
    @Test
    fun testJournalEntryCreation() {
        val entry = JournalEntry(
            content = "Test journal entry",
            reflection = "Test reflection",
            mood = "Happy",
            tags = listOf("Test", "Sample")
        )
        
        assertEquals("Test journal entry", entry.content)
        assertEquals("Happy", entry.mood)
        assertEquals(2, entry.tags.size)
        assertTrue(entry.tags.contains("Test"))
        assertTrue(entry.tags.contains("Sample"))
    }
    
    @Test
    fun testAIServiceReflection() = runBlocking {
        val aiService = AIService()
        val content = "I'm feeling happy today because I accomplished my goals!"
        
        val response = aiService.generateReflection(content)
        
        assertNotNull(response.reflection)
        assertNotNull(response.mood)
        assertNotNull(response.tags)
        assertTrue(response.reflection.isNotEmpty())
        assertTrue(response.mood.isNotEmpty())
    }
    
    @Test
    fun testAIServiceMemorySearch() = runBlocking {
        val aiService = AIService()
        val entries = listOf(
            JournalEntry(
                content = "I was overwhelmed with work today",
                reflection = "Work stress is affecting you",
                mood = "Anxious",
                tags = listOf("Work", "Stress")
            ),
            JournalEntry(
                content = "I felt creative and inspired",
                reflection = "Creative energy is flowing",
                mood = "Creative",
                tags = listOf("Creativity", "Inspiration")
            )
        )
        
        val query = "When did I feel overwhelmed?"
        val result = aiService.searchMemories(query, entries)
        
        assertNotNull(result)
        assertTrue(result.isNotEmpty())
    }
} 