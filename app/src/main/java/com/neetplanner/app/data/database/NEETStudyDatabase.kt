package com.neetplanner.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.neetplanner.app.data.dao.DailyProgressDao
import com.neetplanner.app.data.dao.StudySessionDao
import com.neetplanner.app.data.dao.UserPreferencesDao
import com.neetplanner.app.data.entity.DailyProgress
import com.neetplanner.app.data.entity.StudySession
import com.neetplanner.app.data.entity.UserPreferences

@Database(
    entities = [StudySession::class, DailyProgress::class, UserPreferences::class],
    version = 1,
    exportSchema = false
)
abstract class NEETStudyDatabase : RoomDatabase() {
    abstract fun studySessionDao(): StudySessionDao
    abstract fun dailyProgressDao(): DailyProgressDao
    abstract fun userPreferencesDao(): UserPreferencesDao

    companion object {
        @Volatile
        private var INSTANCE: NEETStudyDatabase? = null

        fun getDatabase(context: Context): NEETStudyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NEETStudyDatabase::class.java,
                    "neet_study_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
