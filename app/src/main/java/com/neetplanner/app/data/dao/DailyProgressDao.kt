package com.neetplanner.app.data.dao

import androidx.room.*
import com.neetplanner.app.data.entity.DailyProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(progress: DailyProgress)

    @Update
    suspend fun update(progress: DailyProgress)

    @Query("SELECT * FROM daily_progress WHERE date = :date")
    suspend fun getProgressByDate(date: String): DailyProgress?

    @Query("SELECT * FROM daily_progress ORDER BY date DESC LIMIT 30")
    fun getLast30DaysProgress(): Flow<List<DailyProgress>>

    @Query("SELECT SUM(totalMinutesStudied) FROM daily_progress")
    fun getTotalMinutesAllTime(): Flow<Int?>

    @Query("SELECT AVG(totalMinutesStudied) FROM daily_progress")
    fun getAverageDailyMinutes(): Flow<Double?>

    @Query("SELECT * FROM daily_progress ORDER BY date DESC LIMIT 7")
    fun getLastWeekProgress(): Flow<List<DailyProgress>>
}
