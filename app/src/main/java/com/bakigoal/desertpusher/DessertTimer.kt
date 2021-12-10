package com.bakigoal.desertpusher

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DessertTimer(lifecycle: Lifecycle) : LifecycleObserver {

    private var secondsCount = 0
    private var executor = scheduledExecutor()

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startTimer() {
        if (executor.isShutdown) executor = scheduledExecutor()
        val runnable = { Timber.i("Timer is at : ${++secondsCount}") }
        executor.scheduleWithFixedDelay(runnable, 1, 1, TimeUnit.SECONDS)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopTimer() = executor.shutdown()

    private fun scheduledExecutor() = Executors.newSingleThreadScheduledExecutor()
}