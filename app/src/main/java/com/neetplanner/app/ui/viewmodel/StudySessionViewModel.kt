package com.neetplanner.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.neetplanner.app.data.entity.StudySession
import com.neetplanner.app.data.repository.StudySessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudySessionViewModel(private val repository: StudySessionRepository) : ViewModel() {
    private val _sessions = MutableStateFlow<List<StudySession>>(emptyList())
    val sessions: StateFlow<List<StudySession>> = _sessions

    private val _selectedSession = MutableStateFlow<StudySession?>(null)
    val selectedSession: StateFlow<StudySession?> = _selectedSession

    private val _totalStudyTime = MutableStateFlow(0)
    val totalStudyTime: StateFlow<Int> = _totalStudyTime

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadAllSessions()
        loadTotalStudyTime()
    }

    fun loadAllSessions() {
        viewModelScope.launch {
            repository.getAllSessions().collect { sessionList ->
                _sessions.value = sessionList
            }
        }
    }

    fun loadSessionsByDate(date: String) {
        viewModelScope.launch {
            repository.getSessionsByDate(date).collect { sessionList ->
                _sessions.value = sessionList
            }
        }
    }

    fun loadSessionsBySubject(subject: String) {
        viewModelScope.launch {
            repository.getSessionsBySubject(subject).collect { sessionList ->
                _sessions.value = sessionList
            }
        }
    }

    fun insertSession(session: StudySession) {
        viewModelScope.launch {
            try {
                repository.insertSession(session)
            } catch (e: Exception) {
                _error.value = "Error adding session: ${e.message}"
            }
        }
    }

    fun updateSession(session: StudySession) {
        viewModelScope.launch {
            try {
                repository.updateSession(session)
            } catch (e: Exception) {
                _error.value = "Error updating session: ${e.message}"
            }
        }
    }

    fun deleteSession(session: StudySession) {
        viewModelScope.launch {
            try {
                repository.deleteSession(session)
            } catch (e: Exception) {
                _error.value = "Error deleting session: ${e.message}"
            }
        }
    }

    fun loadSessionById(id: Int) {
        viewModelScope.launch {
            try {
                _selectedSession.value = repository.getSessionById(id)
            } catch (e: Exception) {
                _error.value = "Error loading session: ${e.message}"
            }
        }
    }

    private fun loadTotalStudyTime() {
        viewModelScope.launch {
            repository.getTotalStudyTime().collect { time ->
                _totalStudyTime.value = time ?: 0
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}

class StudySessionViewModelFactory(private val repository: StudySessionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudySessionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudySessionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
