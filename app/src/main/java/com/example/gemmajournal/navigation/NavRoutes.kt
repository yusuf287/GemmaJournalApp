package com.example.gemmajournal.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home") // Added Home route
    object NewEntry : NavRoutes("new_entry")
    object Reflection : NavRoutes("reflection/{entryId}") {
        fun createRoute(entryId: Long) = "reflection/$entryId"
    }
    object MemoryRecall : NavRoutes("memory_recall")
    object Timeline : NavRoutes("timeline")
} 