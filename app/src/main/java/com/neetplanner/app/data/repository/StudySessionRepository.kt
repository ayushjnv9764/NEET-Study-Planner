package com.neetplanner.app.data.repository

import com.neetplanner.app.data.dao.StudySessionDao
import com.neetplanner.app.data.entity.StudySession
import kotlinx.coroutines.flow.Flow

class StudySessionRepository(private val studySessionDao: StudySessionDao) {
    suspend fun insertSession(session: StudySession) = studySessionDao.insert(session)

    suspend fun updateSession(session: StudySession) = studySessionDao.update(session)

    suspend fun deleteSession(session: StudySession) = studySessionDao.delete(session)

    suspend fun deleteSessionById(id: Int) = studySessionDao.deleteById(id)

    suspend fun getSessionById(id: Int) = studySessionDao.getSessionById(id)

    fun getAllSessions(): Flow<List<StudySession>> = studySessionDao.getAllSessions()

    fun getSessionsByDate(date: String): Flow<List<StudySession>> = studySessionDao.getSessionsByDate(date)

    fun getSessionsBySubject(subject: String): Flow<List<StudySession>> = studySessionDao.getSessionsBySubject(subject)

    fun getSessionsByStatus(status: String): Flow<List<StudySession>> = studySessionDao.getSessionsByStatus(status)

    fun getTotalStudyTime(): Flow<Int?> = studySessionDao.getTotalStudyTime()

    fun getCompletedSessionsCount(date: String): Flow<Int> = studySessionDao.getCompletedSessionsCount(date)
}
