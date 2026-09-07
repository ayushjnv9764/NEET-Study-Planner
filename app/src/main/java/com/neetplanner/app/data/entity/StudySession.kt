package com.neetplanner.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "study_sessions")
data class StudySession(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val subject: String,
    val topic: String,
    val duration: Int, // in minutes
    val date: String,
    val time: String,
    val status: String, // PENDING, IN_PROGRESS, COMPLETED
    val notes: String = "",
    val createdAt: String = LocalDateTime.now().toString(),
    val completedAt: String? = null
)

enum class StudyStatus {
    PENDING, IN_PROGRESS, COMPLETED
}