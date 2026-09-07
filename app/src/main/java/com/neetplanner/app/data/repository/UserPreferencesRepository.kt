package com.neetplanner.app.data.repository

import com.neetplanner.app.data.dao.UserPreferencesDao
import com.neetplanner.app.data.entity.UserPreferences
import kotlinx.coroutines.flow.Flow

class UserPreferencesRepository(private val userPreferencesDao: UserPreferencesDao) {
    suspend fun insertPreferences(preferences: UserPreferences) = userPreferencesDao.insert(preferences)

    suspend fun updatePreferences(preferences: UserPreferences) = userPreferencesDao.update(preferences)

    fun getPreferences(): Flow<UserPreferences> = userPreferencesDao.getPreferences()

    suspend fun updateDailyGoal(goal: Int) = userPreferencesDao.updateDailyGoal(goal)

    suspend fun updateNotifications(enabled: Boolean) = userPreferencesDao.updateNotifications(enabled)
}
