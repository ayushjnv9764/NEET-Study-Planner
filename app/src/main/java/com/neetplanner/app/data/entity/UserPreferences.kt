package com.neetplanner.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_preferences")
data class UserPreferences(
    @PrimaryKey
    val id: Int = 1,
    val dailyStudyGoal: Int = 360, // in minutes (6 hours)
    val notificationsEnabled: Boolean = true,
    val breakDuration: Int = 5, // in minutes
    val sessionDuration: Int = 25 // in minutes (Pomodoro)
)