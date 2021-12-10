package com.bakigoal.desertpusher

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import timber.log.Timber
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DessertTimer(lifecycle: Lifecycle) : LifecycleObserver, LifecycleEventObserver {

    private var secondsCount = 0
    private var executor = scheduledExecutor()

    init {
        lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> startTimer()
            Lifecycle.Event.ON_STOP -> stopTimer()
            else -> {}
        }
    }

    private fun startTimer() {
        if (executor.isShutdown) executor = scheduledExecutor()
        val runnable = { Timber.i("Timer is at : ${++secondsCount}") }
        executor.scheduleWithFixedDelay(runnable, 1, 1, TimeUnit.SECONDS)
    }

    private fun stopTimer() = executor.shutdown()

    private fun scheduledExecutor() = Executors.newSingleThreadScheduledExecutor()

}