package com.example.gemmajournal

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    
    @Test
    fun testJournalEntryCreation() {
        val entry = com.example.gemmajournal.data.JournalEntry(
            content = "Test entry",
            reflection = "Test reflection",
            mood = "Happy",
            tags = listOf("Test", "Sample")
        )
        
        assertEquals("Test entry", entry.content)
        assertEquals("Happy", entry.mood)
        assertEquals(2, entry.tags.size)
    }
}