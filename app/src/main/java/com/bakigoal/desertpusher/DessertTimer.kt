package com.bakigoal.desertpusher

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import timber.log.Timber
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DessertTimer(lifecycle: Lifecycle) : DefaultLifecycleObserver {

    private var secondsCount = 0
    private var executor = scheduledExecutor()

    init {
        lifecycle.addObserver(this)
    }

    override fun onStart(owner: LifecycleOwner) {
        startTimer()
    }

    override fun onStop(owner: LifecycleOwner) {
        stopTimer()
    }

    private fun startTimer() {
        if (executor.isShutdown) executor = scheduledExecutor()
        val runnable = { Timber.i("Timer is at : ${++secondsCount}") }
        executor.scheduleWithFixedDelay(runnable, 1, 1, TimeUnit.SECONDS)
    }

    private fun stopTimer() = executor.shutdown()

    private fun scheduledExecutor() = Executors.newSingleThreadScheduledExecutor()

}