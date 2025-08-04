# Gemma Journal

A fully offline, AI-powered journaling assistant that helps users privately reflect using typed or voice input. It runs entirely on-device—no servers, no cloud—using local language models to generate thoughtful reflections, emotional summaries, and memory-based insights.

## Features

### 🖊️ New Entry Screen
- **Text Input**: Auto-expanding text area for journal entries
- **Voice Recording**: Placeholder for whisper.cpp integration
- **AI Reflection**: Automatic generation of thoughtful reflections
- **Mood Detection**: AI-powered mood and emotion analysis
- **Smart Tagging**: Automatic categorization of entries

### 🤖 Reflection View
- **AI Insights**: Display AI-generated reflections and insights
- **Mood & Tags**: Show detected mood and relevant tags
- **Editable Tags**: Users can edit AI-generated tags
- **Navigation**: Easy access to other screens

### 🔍 Memory Recall
- **Natural Language Search**: Ask questions about past entries
- **Semantic Search**: Find entries by mood, themes, or content
- **Suggested Prompts**: Helpful question suggestions
- **AI Summaries**: Intelligent responses to memory queries

### 📅 Timeline Log
- **Chronological View**: Browse all journal entries
- **Expandable Cards**: Tap to see full entries and reflections
- **Quick Actions**: View reflections, edit, or delete entries
- **Empty State**: Encouraging message for new users

## Technical Architecture

### Data Layer
- **Room Database**: Local SQLite database for journal entries
- **Repository Pattern**: Clean separation of data operations
- **Type Converters**: Handle LocalDateTime and List<String> serialization

### AI Integration
- **Mock AI Service**: Currently uses pattern matching for demo
- **Extensible Design**: Easy to replace with actual local LLM
- **Offline-First**: All processing happens on-device

### UI/UX
- **Material Design 3**: Modern, accessible interface
- **Jetpack Compose**: Declarative UI framework
- **Navigation**: Single Activity with Compose Navigation
- **State Management**: ViewModels with StateFlow

## Screen Flows

```
[New Entry Screen]
   ↓ Submit
[Reflection View]
   → Save → [Timeline Log]
   → "Ask About Past" → [Memory Recall]
   → "New Entry" → [New Entry Screen]

[Memory Recall]
   → Results → [Timeline Log] or show AI summary
   → "New Entry" → [New Entry Screen]

[Timeline Log]
   → Click Entry → [Reflection View]
   → "New Entry" → [New Entry Screen]
```

## Future Enhancements

### AI Integration
- **Local LLM**: Integrate with Gemma or similar local models
- **Whisper.cpp**: Offline speech-to-text processing
- **Embeddings**: Vector search for better memory recall
- **Fine-tuning**: Custom model training for personal insights

### Features
- **Voice Input**: Real-time speech-to-text
- **Image Support**: Photo journaling capabilities
- **Mood Tracking**: Visual mood trends and patterns
- **Export**: Backup and export functionality
- **Themes**: Dark/light mode and customization

### Wellness Features
- **Gratitude Prompts**: Guided gratitude journaling
- **Goal Tracking**: Personal goal setting and progress
- **Habit Building**: Streak tracking and reminders
- **Insights**: Weekly/monthly emotional summaries

## Getting Started

1. **Clone the repository**
2. **Open in Android Studio**
3. **Build and run** on an Android device or emulator
4. **Start journaling** with the New Entry screen

## Dependencies

- **Jetpack Compose**: Modern UI toolkit
- **Room**: Local database
- **Navigation**: Screen navigation
- **Lifecycle**: ViewModels and state management
- **Coroutines**: Asynchronous programming
- **Material 3**: Design system

## Privacy & Security

- **100% Offline**: No data leaves your device
- **Local Storage**: All data stored on-device
- **No Analytics**: No tracking or data collection
- **Open Source**: Transparent codebase

## Contributing

This is a personal project focused on privacy and wellness. Feel free to fork and customize for your own needs!

---

*Built with ❤️ for mindful journaling and personal growth* 