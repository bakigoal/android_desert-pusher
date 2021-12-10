package com.bakigoal.desertpusher

import timber.log.Timber
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DessertTimer {

    private var secondsCount = 0
    private var executor = scheduledExecutor()

    fun startTimer() {
        if (executor.isShutdown) executor = scheduledExecutor()
        val runnable = { Timber.i("Timer is at : ${++secondsCount}") }
        executor.scheduleWithFixedDelay(runnable, 1, 1, TimeUnit.SECONDS)
    }

    fun stopTimer() = executor.shutdown()

    private fun scheduledExecutor() = Executors.newSingleThreadScheduledExecutor()
}