package com.example.applicationplage.framework

import android.content.Context
import android.content.Intent
import androidx.work.Worker
import androidx.work.WorkerParameters
import android.util.Log


class NotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    val TAG = "PlageNotificationWorker"

    override fun doWork() : Result {
        Log.d(TAG, "doWork")
        applicationContext.startService((Intent(applicationContext, NotificationService::class.java)))
        return Result.success()
    }
}