package com.neetplanner.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.neetplanner.app.data.entity.DailyProgress
import com.neetplanner.app.data.repository.DailyProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProgressViewModel(private val repository: DailyProgressRepository) : ViewModel() {
    private val _lastWeekProgress = MutableStateFlow<List<DailyProgress>>(emptyList())
    val lastWeekProgress: StateFlow<List<DailyProgress>> = _lastWeekProgress

    private val _totalMinutes = MutableStateFlow(0)
    val totalMinutes: StateFlow<Int> = _totalMinutes

    private val _averageDailyMinutes = MutableStateFlow(0.0)
    val averageDailyMinutes: StateFlow<Double> = _averageDailyMinutes

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadLastWeekProgress()
        loadTotalMinutes()
        loadAverageDailyMinutes()
    }

    private fun loadLastWeekProgress() {
        viewModelScope.launch {
            repository.getLastWeekProgress().collect { progress ->
                _lastWeekProgress.value = progress
            }
        }
    }

    private fun loadTotalMinutes() {
        viewModelScope.launch {
            repository.getTotalMinutesAllTime().collect { minutes ->
                _totalMinutes.value = minutes ?: 0
            }
        }
    }

    private fun loadAverageDailyMinutes() {
        viewModelScope.launch {
            repository.getAverageDailyMinutes().collect { average ->
                _averageDailyMinutes.value = average ?: 0.0
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}

class ProgressViewModelFactory(private val repository: DailyProgressRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProgressViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProgressViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
