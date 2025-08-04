package com.example.gemmajournal.data

import java.time.LocalDateTime

object DemoData {
    
    fun generateSampleEntries(): List<JournalEntry> {
        return listOf(
            JournalEntry(
                id = 1,
                content = "Today was amazing! I finally finished that project I've been working on for weeks. The sense of accomplishment is incredible. I feel like I've grown so much through this process.",
                reflection = "You're experiencing a well-deserved sense of achievement. Completing long-term projects can be incredibly rewarding and validating. This success likely reflects your persistence and dedication.",
                mood = "Happy",
                tags = listOf("Work", "Achievement", "Growth"),
                createdAt = LocalDateTime.now().minusDays(1)
            ),
            JournalEntry(
                id = 2,
                content = "I'm feeling a bit overwhelmed today. There's so much to do and I'm not sure where to start. Maybe I need to take a step back and prioritize better.",
                reflection = "It's natural to feel overwhelmed when facing multiple responsibilities. Consider breaking down your tasks into smaller, manageable steps. Remember that it's okay to ask for help when needed.",
                mood = "Anxious",
                tags = listOf("Stress", "Work", "Self-care"),
                createdAt = LocalDateTime.now().minusDays(2)
            ),
            JournalEntry(
                id = 3,
                content = "Had a wonderful conversation with my friend today. We talked about our dreams and aspirations. It reminded me how important it is to have people who truly understand you.",
                reflection = "Meaningful connections with friends can be deeply nourishing. These conversations often help us clarify our own thoughts and feelings. Your appreciation for authentic relationships shows emotional intelligence.",
                mood = "Grateful",
                tags = listOf("Relationships", "Friendship", "Connection"),
                createdAt = LocalDateTime.now().minusDays(3)
            ),
            JournalEntry(
                id = 4,
                content = "I'm feeling creative today! Started working on a new painting and the ideas are flowing. It's amazing how art can be such a powerful form of self-expression.",
                reflection = "Your creative energy is flowing beautifully today. Art can indeed be a powerful medium for processing emotions and expressing your inner world. This creative outlet seems to be serving you well.",
                mood = "Creative",
                tags = listOf("Art", "Creativity", "Self-expression"),
                createdAt = LocalDateTime.now().minusDays(4)
            ),
            JournalEntry(
                id = 5,
                content = "Feeling tired but accomplished. It's been a long week but I've made good progress on my goals. Time to rest and recharge for the weekend.",
                reflection = "You're showing good self-awareness by recognizing your need for rest after a productive week. Balancing achievement with self-care is essential for sustainable growth and well-being.",
                mood = "Tired",
                tags = listOf("Goals", "Rest", "Progress"),
                createdAt = LocalDateTime.now().minusDays(5)
            )
        )
    }
    
    fun getSampleQueryResults(): Map<String, String> {
        return mapOf(
            "When was I most creative?" to "I found 1 relevant entries.\n\nDec 20:\n\"I'm feeling creative today! Started working on a new painting and the ideas are flowing...\"\nMood: Creative",
            "When did I feel overwhelmed?" to "I found 1 relevant entries.\n\nDec 19:\n\"I'm feeling a bit overwhelmed today. There's so much to do and I'm not sure where to start...\"\nMood: Anxious",
            "When was I happiest?" to "I found 1 relevant entries.\n\nDec 21:\n\"Today was amazing! I finally finished that project I've been working on for weeks...\"\nMood: Happy"
        )
    }
} 