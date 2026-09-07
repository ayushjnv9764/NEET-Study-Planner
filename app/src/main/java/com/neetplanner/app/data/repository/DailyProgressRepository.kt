package com.neetplanner.app.data.repository

import com.neetplanner.app.data.dao.DailyProgressDao
import com.neetplanner.app.data.entity.DailyProgress
import kotlinx.coroutines.flow.Flow

class DailyProgressRepository(private val dailyProgressDao: DailyProgressDao) {
    suspend fun insertProgress(progress: DailyProgress) = dailyProgressDao.insert(progress)

    suspend fun updateProgress(progress: DailyProgress) = dailyProgressDao.update(progress)

    suspend fun getProgressByDate(date: String) = dailyProgressDao.getProgressByDate(date)

    fun getLast30DaysProgress(): Flow<List<DailyProgress>> = dailyProgressDao.getLast30DaysProgress()

    fun getTotalMinutesAllTime(): Flow<Int?> = dailyProgressDao.getTotalMinutesAllTime()

    fun getAverageDailyMinutes(): Flow<Double?> = dailyProgressDao.getAverageDailyMinutes()

    fun getLastWeekProgress(): Flow<List<DailyProgress>> = dailyProgressDao.getLastWeekProgress()
}
