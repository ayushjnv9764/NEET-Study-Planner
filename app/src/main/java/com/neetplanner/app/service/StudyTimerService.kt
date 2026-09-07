package com.neetplanner.app.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.*

class StudyTimerService : Service() {
    private val binder = LocalBinder()
    private var timerJob: Job? = null
    private var elapsedSeconds = 0
    private var isRunning = false

    inner class LocalBinder : Binder() {
        fun getService(): StudyTimerService = this@StudyTimerService
    }

    override fun onBind(intent: Intent?): IBinder? = binder

    fun startTimer() {
        if (isRunning) return
        isRunning = true
        timerJob = CoroutineScope(Dispatchers.Main).launch {
            while (isRunning) {
                delay(1000)
                elapsedSeconds++
            }
        }
    }

    fun pauseTimer() {
        isRunning = false
    }

    fun resumeTimer() {
        startTimer()
    }

    fun stopTimer() {
        timerJob?.cancel()
        isRunning = false
        elapsedSeconds = 0
    }

    fun getElapsedTime(): String {
        val hours = elapsedSeconds / 3600
        val minutes = (elapsedSeconds % 3600) / 60
        val seconds = elapsedSeconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    override fun onDestroy() {
        super.onDestroy()
        timerJob?.cancel()
    }
}
