package com.neetplanner.app.util

import com.neetplanner.app.data.entity.StudySession

object GamificationEngine {
    data class Achievement(
        val id: Int,
        val name: String,
        val description: String,
        val points: Int,
        val icon: String
    )

    data class UserStats(
        val totalPoints: Int = 0,
        val streak: Int = 0,
        val level: Int = 1,
        val achievements: List<Achievement> = emptyList()
    )

    private val achievements = listOf(
        Achievement(1, "First Step", "Complete your first study session", 10, "🎯"),
        Achievement(2, "One Hour Club", "Study for 1 hour", 25, "⏱️"),
        Achievement(3, "Five Hour Warrior", "Study for 5 hours", 50, "⚔️"),
        Achievement(4, "Weekly Champion", "Study every day for a week", 100, "🏆"),
        Achievement(5, "Subject Master - Physics", "Complete 10 Physics sessions", 75, "⚛️"),
        Achievement(6, "Subject Master - Chemistry", "Complete 10 Chemistry sessions", 75, "🧪"),
        Achievement(7, "Subject Master - Biology", "Complete 10 Biology sessions", 75, "🔬"),
        Achievement(8, "Consistency King", "Maintain a 30-day streak", 200, "👑"),
        Achievement(9, "Early Bird", "Complete a session before 8 AM", 20, "🌅"),
        Achievement(10, "Night Owl", "Complete a session after 10 PM", 20, "🦉")
    )

    fun calculatePoints(sessions: List<StudySession>): Int {
        val completedSessions = sessions.filter { it.status == "COMPLETED" }
        val totalMinutes = completedSessions.sumOf { it.duration }
        val basePoints = (totalMinutes / 10) * 5 // 5 points per 10 minutes
        return basePoints
    }

    fun calculateLevel(points: Int): Int {
        return (points / 100) + 1
    }

    fun getUnlockedAchievements(sessions: List<StudySession>): List<Achievement> {
        val unlockedAchievements = mutableListOf<Achievement>()
        val completedSessions = sessions.filter { it.status == "COMPLETED" }
        val totalMinutes = completedSessions.sumOf { it.duration }
        val physicsCount = completedSessions.count { it.subject == "Physics" }
        val chemistryCount = completedSessions.count { it.subject == "Chemistry" }
        val biologyCount = completedSessions.count { it.subject == "Biology" }

        if (completedSessions.isNotEmpty()) unlockedAchievements.add(achievements[0])
        if (totalMinutes >= 60) unlockedAchievements.add(achievements[1])
        if (totalMinutes >= 300) unlockedAchievements.add(achievements[2])
        if (physicsCount >= 10) unlockedAchievements.add(achievements[4])
        if (chemistryCount >= 10) unlockedAchievements.add(achievements[5])
        if (biologyCount >= 10) unlockedAchievements.add(achievements[6])

        return unlockedAchievements
    }

    fun getNextAchievements(sessions: List<StudySession>): List<Achievement> {
        val completed = getUnlockedAchievements(sessions).map { it.id }
        return achievements.filter { !completed.contains(it.id) }
    }

    fun generateStudyTip(): String {
        val tips = listOf(
            "💡 Take short breaks every 25 minutes using the Pomodoro technique!",
            "💡 Solve previous year's NEET questions for better preparation!",
            "💡 Review your notes within 24 hours for better retention!",
            "💡 Join study groups to discuss difficult concepts!",
            "💡 Focus on high-weighted topics first!",
            "💡 Practice time management in your mock tests!",
            "💡 Sleep well - 7-8 hours is crucial for memory consolidation!",
            "💡 Stay hydrated and take care of your physical health!",
            "💡 Use active recall instead of passive reading!",
            "💡 Create concept maps to visualize complex topics!"
        )
        return tips.random()
    }
}
