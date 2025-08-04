package com.example.gemmajournal.ai

import kotlinx.coroutines.delay

data class AIResponse(
    val reflection: String,
    val mood: String,
    val tags: List<String>,
    val insight: String?
)

class AIService {
    
    suspend fun generateReflection(content: String): AIResponse {
        // Simulate AI processing time
        delay(1000)
        
        // Mock AI response - in real implementation, this would call a local LLM
        val reflection = when {
            content.contains("happy") || content.contains("joy") || content.contains("excited") -> 
                "You seem to be experiencing positive emotions today. Your energy and enthusiasm are palpable in your writing."
            content.contains("sad") || content.contains("depressed") || content.contains("down") -> 
                "I notice you're feeling low today. It's okay to acknowledge these feelings. Consider what might be contributing to this mood."
            content.contains("angry") || content.contains("frustrated") || content.contains("mad") -> 
                "You're expressing some strong emotions. Anger can be a signal that something important to you has been affected."
            content.contains("anxious") || content.contains("worried") || content.contains("stress") -> 
                "I sense some anxiety in your thoughts. What specific concerns are weighing on your mind?"
            content.contains("work") || content.contains("job") || content.contains("career") -> 
                "Work-related thoughts are prominent today. How are you feeling about your professional life?"
            content.contains("family") || content.contains("friend") || content.contains("relationship") -> 
                "Your relationships seem to be on your mind. How are your connections with others?"
            else -> 
                "Thank you for sharing your thoughts. I can see you're processing various aspects of your day."
        }
        
        val mood = when {
            content.contains("happy") || content.contains("joy") || content.contains("excited") -> "Happy"
            content.contains("sad") || content.contains("depressed") || content.contains("down") -> "Sad"
            content.contains("angry") || content.contains("frustrated") || content.contains("mad") -> "Angry"
            content.contains("anxious") || content.contains("worried") || content.contains("stress") -> "Anxious"
            content.contains("tired") || content.contains("exhausted") || content.contains("drained") -> "Tired"
            else -> "Neutral"
        }
        
        val tags = mutableListOf<String>()
        if (content.contains("work") || content.contains("job")) tags.add("Work")
        if (content.contains("family") || content.contains("friend")) tags.add("Relationships")
        if (content.contains("health") || content.contains("exercise")) tags.add("Health")
        if (content.contains("creative") || content.contains("art") || content.contains("music")) tags.add("Creativity")
        if (content.contains("future") || content.contains("goal")) tags.add("Goals")
        if (content.contains("past") || content.contains("memory")) tags.add("Memories")
        
        val insight = when {
            content.contains("overwhelmed") -> "Consider breaking down your tasks into smaller, manageable steps."
            content.contains("grateful") -> "Gratitude is a powerful practice. Keep noticing the good things in your life."
            content.contains("uncertain") -> "Uncertainty is a natural part of growth. What would help you feel more grounded?"
            else -> null
        }
        
        return AIResponse(reflection, mood, tags, insight)
    }
    
    suspend fun searchMemories(query: String, entries: List<com.example.gemmajournal.data.JournalEntry>): String {
        delay(500)
        
        val relevantEntries = entries.filter { entry ->
            entry.content.contains(query, ignoreCase = true) ||
            entry.reflection.contains(query, ignoreCase = true) ||
            entry.mood.contains(query, ignoreCase = true) ||
            entry.tags.any { it.contains(query, ignoreCase = true) }
        }
        
        return if (relevantEntries.isNotEmpty()) {
            "I found ${relevantEntries.size} relevant entries. Here are some highlights:\n\n" +
            relevantEntries.take(3).joinToString("\n\n") { entry ->
                "${entry.createdAt.format(java.time.format.DateTimeFormatter.ofPattern("MMM dd"))}:\n" +
                "\"${entry.content.take(100)}${if (entry.content.length > 100) "..." else ""}\"\n" +
                "Mood: ${entry.mood}"
            }
        } else {
            "I couldn't find any entries matching your query. Try searching for different keywords or emotions."
        }
    }
} 