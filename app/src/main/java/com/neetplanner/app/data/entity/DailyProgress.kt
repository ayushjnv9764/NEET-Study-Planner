package com.neetplanner.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "daily_progress")
data class DailyProgress(
    @PrimaryKey
    val date: String,
    val totalMinutesStudied: Int = 0,
    val sessionsCompleted: Int = 0,
    val physicsMinutes: Int = 0,
    val chemistryMinutes: Int = 0,
    val biologyMinutes: Int = 0
)