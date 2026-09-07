package com.neetplanner.app.data.dao

import androidx.room.*
import com.neetplanner.app.data.entity.UserPreferences
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPreferencesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(preferences: UserPreferences)

    @Update
    suspend fun update(preferences: UserPreferences)

    @Query("SELECT * FROM user_preferences WHERE id = 1")
    fun getPreferences(): Flow<UserPreferences>

    @Query("UPDATE user_preferences SET dailyStudyGoal = :goal WHERE id = 1")
    suspend fun updateDailyGoal(goal: Int)

    @Query("UPDATE user_preferences SET notificationsEnabled = :enabled WHERE id = 1")
    suspend fun updateNotifications(enabled: Boolean)
}
