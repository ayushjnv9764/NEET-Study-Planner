package com.neetplanner.app.util

import com.neetplanner.app.data.entity.StudySession

object AIRecommendationEngine {
    data class StudyRecommendation(
        val title: String,
        val description: String,
        val priority: String, // HIGH, MEDIUM, LOW
        val suggestedDuration: Int // in minutes
    )

    fun generateRecommendations(sessions: List<StudySession>): List<StudyRecommendation> {
        val recommendations = mutableListOf<StudyRecommendation>()
        val completedSessions = sessions.filter { it.status == "COMPLETED" }
        val pendingSessions = sessions.filter { it.status == "PENDING" }
        val physicsCount = completedSessions.count { it.subject == "Physics" }
        val chemistryCount = completedSessions.count { it.subject == "Chemistry" }
        val biologyCount = completedSessions.count { it.subject == "Biology" }
        val totalMinutes = completedSessions.sumOf { it.duration }

        // Recommendation based on subject balance
        val minCount = minOf(physicsCount, chemistryCount, biologyCount)
        if (physicsCount == minCount && physicsCount < 10) {
            recommendations.add(
                StudyRecommendation(
                    "Focus on Physics",
                    "You're studying Physics less compared to other subjects. Balance is key!",
                    "HIGH",
                    90
                )
            )
        }
        if (chemistryCount == minCount && chemistryCount < 10) {
            recommendations.add(
                StudyRecommendation(
                    "Focus on Chemistry",
                    "Chemistry needs more attention. Try to balance your preparation!",
                    "HIGH",
                    90
                )
            )
        }
        if (biologyCount == minCount && biologyCount < 10) {
            recommendations.add(
                StudyRecommendation(
                    "Focus on Biology",
                    "Biology is lagging behind. Increase your study hours for this subject!",
                    "HIGH",
                    90
                )
            )
        }

        // Recommendation based on study time
        if (totalMinutes < 360) {
            recommendations.add(
                StudyRecommendation(
                    "Increase Study Hours",
                    "You need to study more to meet your daily goal of 6 hours!",
                    "HIGH",
                    120
                )
            )
        }

        // Recommendation for pending sessions
        if (pendingSessions.size > 5) {
            recommendations.add(
                StudyRecommendation(
                    "Complete Pending Sessions",
                    "You have ${pendingSessions.size} pending sessions. Try to complete them!",
                    "MEDIUM",
                    60
                )
            )
        }

        // Streak recommendation
        if (completedSessions.isNotEmpty()) {
            recommendations.add(
                StudyRecommendation(
                    "Maintain Your Streak",
                    "Keep studying consistently to build a winning streak!",
                    "MEDIUM",
                    45
                )
            )
        }

        return recommendations
    }

    fun getOptimalStudySchedule(): String {
        return """
        📚 Optimal NEET Study Schedule:
        
        6:00 AM - 7:00 AM: Physical Exercise & Breakfast
        7:00 AM - 9:00 AM: High-focus subject (Physics/Chemistry)
        9:00 AM - 10:00 AM: Break & Revision
        10:00 AM - 12:00 PM: Medium-focus subject (Biology)
        12:00 PM - 1:00 PM: Lunch Break
        1:00 PM - 3:00 PM: Practice problems
        3:00 PM - 4:00 PM: Light subject review
        4:00 PM - 6:00 PM: Mock tests or Previous year questions
        6:00 PM - 7:00 PM: Break & Refresh
        7:00 PM - 9:00 PM: Conceptual clarity & Notes
        9:00 PM onwards: Sleep preparation
        """.trimIndent()
    }
}
