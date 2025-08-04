# Build Instructions for Gemma Journal

## Prerequisites

### 1. Install Java Development Kit (JDK)
The project requires JDK 17 or higher. You can install it using:

**On macOS:**
```bash
# Using Homebrew
brew install openjdk@17

# Or download from Oracle
# https://www.oracle.com/java/technologies/downloads/#java17
```

**On Windows:**
- Download JDK 17 from Oracle or use OpenJDK
- Add JAVA_HOME to your environment variables

**On Linux:**
```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

### 2. Verify Java Installation
```bash
java -version
javac -version
```

### 3. Set JAVA_HOME (if needed)
```bash
# macOS/Linux
export JAVA_HOME=/path/to/your/jdk
export PATH=$JAVA_HOME/bin:$PATH

# Windows
set JAVA_HOME=C:\path\to\your\jdk
set PATH=%JAVA_HOME%\bin;%PATH%
```

## Building the Project

### Option 1: Using Android Studio
1. Open Android Studio
2. Open the project: `File > Open > Select GemmaJournal folder`
3. Wait for Gradle sync to complete
4. Click the "Run" button (green play icon)

### Option 2: Using Command Line
```bash
# Navigate to project directory
cd /path/to/GemmaJournal

# Clean and build
./gradlew clean
./gradlew build

# Run on connected device/emulator
./gradlew installDebug
```

### Option 3: Using Gradle Wrapper
```bash
# Make gradlew executable (macOS/Linux)
chmod +x gradlew

# Build the project
./gradlew assembleDebug
```

## Troubleshooting

### Java Not Found Error
If you get "Unable to locate a Java Runtime":
1. Install JDK 17 or higher
2. Set JAVA_HOME environment variable
3. Add Java to your PATH

### Gradle Sync Issues
1. In Android Studio: `File > Invalidate Caches and Restart`
2. Delete `.gradle` folder and rebuild
3. Check internet connection for dependency downloads

### Build Errors
1. Make sure all dependencies are resolved
2. Check that all imports are correct
3. Verify that Room database annotations are properly set up

## Project Structure

```
GemmaJournal/
├── app/
│   ├── src/main/java/com/example/gemmajournal/
│   │   ├── data/           # Database models and DAOs
│   │   ├── ai/             # AI service (mock implementation)
│   │   ├── ui/             # UI components and screens
│   │   ├── navigation/     # Navigation setup
│   │   └── theme/          # App theming
│   └── build.gradle.kts    # App dependencies
├── gradle/
├── build.gradle.kts        # Project configuration
└── settings.gradle.kts     # Project settings
```

## Features Implemented

✅ **New Entry Screen** - Text input with AI reflection generation
✅ **Reflection View** - Display AI insights with editable tags
✅ **Memory Recall** - Natural language search through past entries
✅ **Timeline Log** - Chronological view of all journal entries
✅ **Room Database** - Local SQLite storage
✅ **Navigation** - Screen-to-screen navigation
✅ **Material Design 3** - Modern UI with custom theme

## Next Steps

1. **Test the app** in Android Studio
2. **Add real AI integration** with local language models
3. **Implement voice recording** with whisper.cpp
4. **Add data export** and backup features
5. **Enhance with wellness features** like mood tracking

## Dependencies

- **Jetpack Compose**: Modern UI toolkit
- **Room**: Local database
- **Navigation**: Screen navigation
- **Lifecycle**: ViewModels and state management
- **Coroutines**: Asynchronous programming
- **Material 3**: Design system

The app is ready to run once Java is properly installed! 