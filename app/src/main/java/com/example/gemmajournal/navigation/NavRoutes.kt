package com.example.gemmajournal.navigation

sealed class NavRoutes(val route: String) {
    object NewEntry : NavRoutes("new_entry")
    object Reflection : NavRoutes("reflection/{entryId}") {
        fun createRoute(entryId: Long) = "reflection/$entryId"
    }
    object MemoryRecall : NavRoutes("memory_recall")
    object Timeline : NavRoutes("timeline")
} 