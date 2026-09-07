package com.neetplanner.app.data.dao

import androidx.room.*
import com.neetplanner.app.data.entity.StudySession
import kotlinx.coroutines.flow.Flow

@Dao
interface StudySessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(session: StudySession)

    @Update
    suspend fun update(session: StudySession)

    @Delete
    suspend fun delete(session: StudySession)

    @Query("SELECT * FROM study_sessions WHERE id = :id")
    suspend fun getSessionById(id: Int): StudySession?

    @Query("SELECT * FROM study_sessions ORDER BY date DESC, time DESC")
    fun getAllSessions(): Flow<List<StudySession>>

    @Query("SELECT * FROM study_sessions WHERE date = :date ORDER BY time DESC")
    fun getSessionsByDate(date: String): Flow<List<StudySession>>

    @Query("SELECT * FROM study_sessions WHERE subject = :subject ORDER BY date DESC")
    fun getSessionsBySubject(subject: String): Flow<List<StudySession>>

    @Query("SELECT * FROM study_sessions WHERE status = :status ORDER BY date DESC")
    fun getSessionsByStatus(status: String): Flow<List<StudySession>>

    @Query("DELETE FROM study_sessions WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT SUM(duration) FROM study_sessions WHERE status = 'COMPLETED'")
    fun getTotalStudyTime(): Flow<Int?>

    @Query("SELECT COUNT(*) FROM study_sessions WHERE status = 'COMPLETED' AND date = :date")
    fun getCompletedSessionsCount(date: String): Flow<Int>
}
