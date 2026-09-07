# NEET Study Planner - Android App

A comprehensive study planning application designed specifically for NEET (National Eligibility cum Entrance Test) exam preparation with advanced features like gamification, AI recommendations, and progress tracking.

## 🌟 Features

### Core Features
- ✅ **Study Session Management**: Create, edit, and track study sessions
- ✅ **Subject Organization**: Separate sessions for Physics, Chemistry, and Biology
- ✅ **Daily Progress Tracking**: Monitor total study hours and daily goals
- ✅ **Weekly Analytics**: Visual progress bars for the last 7 days
- ✅ **Local Database**: All data stored locally using Room Database

### Gamification Features 🎮
- 🏆 **Achievement System**: Unlock 10+ achievements as you study
  - First Step: Complete first session
  - One Hour Club: Study for 1 hour
  - Subject Masters: Complete 10 sessions per subject
  - Weekly Champion: Study every day for a week
  - And more!
- 💎 **Points & Levels**: Earn points for every study session
- 🔥 **Streak Tracking**: Maintain daily study streaks
- 📊 **Leaderboard Ready**: Framework for competitive study tracking

### AI-Powered Features 🤖
- 🎯 **Smart Recommendations**: Get personalized study suggestions based on your progress
- ⚡ **Subject Balance Detection**: AI identifies which subjects need more focus
- 📈 **Progress Analysis**: Automatic analysis of study patterns
- 💡 **Daily Study Tips**: Get random NEET preparation tips
- 🕐 **Optimal Study Schedule**: Pre-designed study timetable for NEET prep

### Notification System 🔔
- 🔊 **Session Reminders**: Get notified about pending study sessions
- 🎉 **Achievement Alerts**: Celebrate when you unlock achievements
- ⏰ **Daily Goal Reminders**: Stay motivated to reach your daily targets
- ✨ **Study Encouragement**: Motivational notifications throughout the day

### Advanced Features
- ⏱️ **Built-in Study Timer**: Track study session duration
- 📱 **Responsive Design**: Material Design 3 interface
- 🌙 **Dark Mode Support**: Eye-friendly theme options
- 📊 **Detailed Statistics**: View comprehensive study analytics
- 🔐 **Data Persistence**: All data saved locally and securely
- 🎨 **Subject-specific Colors**: Physics (Blue), Chemistry (Orange), Biology (Green)

## 📋 Project Structure

```
app/src/main/java/com/neetplanner/app/
├── ui/
│   ├── MainActivity.kt
│   ├── adapter/
│   │   └── StudySessionAdapter.kt
│   ├── fragments/
│   │   ├── HomeFragment.kt
│   │   ├── ScheduleFragment.kt
│   │   ├── ProgressFragment.kt
│   │   ├── SettingsFragment.kt
│   │   └── AddStudySessionFragment.kt
│   └── viewmodel/
│       ├── StudySessionViewModel.kt
│       └── ProgressViewModel.kt
├── data/
│   ├── database/
│   │   └── NEETStudyDatabase.kt
│   ├── dao/
│   │   ├── StudySessionDao.kt
│   │   ├── DailyProgressDao.kt
│   │   └── UserPreferencesDao.kt
│   ├── entity/
│   │   ├── StudySession.kt
│   │   ├── DailyProgress.kt
│   │   └── UserPreferences.kt
│   └── repository/
│       ├── StudySessionRepository.kt
│       ├── DailyProgressRepository.kt
│       └── UserPreferencesRepository.kt
├── service/
│   └── StudyTimerService.kt
└── util/
    ├── NotificationHelper.kt
    ├── GamificationEngine.kt
    └── AIRecommendationEngine.kt
```

## 🛠️ Technologies Used

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose ready, Material Design 3
- **Database**: Room Database
- **Architecture**: MVVM (Model-View-ViewModel)
- **Async**: Coroutines & Flow
- **Minimum SDK**: Android 7.0 (API 24)
- **Target SDK**: Android 14 (API 34)

## 📱 App Screens

### 1. **Home Screen**
   - Today's study sessions overview
   - Total hours studied counter
   - Completed sessions count
   - Quick access to add new sessions

### 2. **Schedule Screen**
   - All study sessions in chronological order
   - Filter by subject (Physics, Chemistry, Biology)
   - Mark sessions as complete
   - Edit or delete sessions

### 3. **Progress Screen**
   - Total hours studied (all-time)
   - Average daily study hours
   - Weekly progress visualization
   - Subject-wise breakdown

### 4. **Settings Screen**
   - Enable/Disable notifications
   - Set daily study goal
   - Customize session duration
   - View achievements

## 🚀 Getting Started

### Prerequisites
- Android Studio (Latest version)
- Java 17 or higher
- Android SDK 34

### Installation

1. Clone the repository
   ```bash
   git clone https://github.com/ayushjnv9764/NEET-Study-Planner.git
   ```

2. Open in Android Studio
   ```bash
   cd NEET-Study-Planner
   ```

3. Build and run
   ```bash
   ./gradlew build
   ```

4. Deploy to emulator or physical device
   ```bash
   ./gradlew installDebug
   ```

## 💡 Usage Examples

### Adding a Study Session
1. Tap the floating action button (+) on any screen
2. Select subject (Physics, Chemistry, Biology)
3. Enter topic name
4. Set duration in minutes
5. Add optional notes
6. Tap Save

### Tracking Progress
1. Go to Progress tab
2. View total hours and daily average
3. Check weekly progress bars
4. Get AI recommendations

### Unlocking Achievements
1. Study consistently
2. Complete sessions
3. Reach milestones
4. View achievements in Settings

## 🎯 NEET Preparation Tips

The app includes intelligent recommendations for:
- Balanced subject preparation
- Optimal study schedule
- Time management strategies
- Consistent streak maintenance
- Daily study goal achievement

## 🐛 Known Issues
- Push notifications require Android 13+
- Dark mode requires manual device setting

## 📈 Future Enhancements

- [ ] Cloud synchronization
- [ ] Social study groups
- [ ] Video tutorial integration
- [ ] Doubt clearing chatbot
- [ ] Subject-wise practice papers
- [ ] Performance analytics dashboard
- [ ] Study buddy matching
- [ ] Offline content support
- [ ] Export study reports
- [ ] Integration with NEET coaching websites

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is open source and available under the MIT License.

## 👨‍💻 Author

**Ayush Jnv**
- GitHub: [@ayushjnv9764](https://github.com/ayushjnv9764)
- Email: ayushjnv9764@gmail.com

## 📞 Support

For support, open an issue on GitHub or contact the author.

## 🎓 Educational Note

This app is designed to help NEET aspirants organize their study schedule and track progress. Success in NEET requires consistent effort, smart preparation, and disciplined study habits. Use this app as a tool to enhance your preparation journey.

**Best of Luck! 🍀**

---

**Made with ❤️ for NEET Aspirants**