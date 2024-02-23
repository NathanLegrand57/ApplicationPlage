package com.example.applicationplage

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

class PlageApplication : Application() {
    val TAG = "PlageApplication"

    override fun onCreate() {
        super.onCreate()

        val notificationManager = getSystemService(NotificationManager::class.java)

        val channel = NotificationChannel(FetchDataChannel, "Récupération des datas", NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = "Accès au serveur pour récupérer les données"
            notificationManager.createNotificationChannel(this)
        }
        NotificationManagerCompat.from(this).apply {
            createNotificationChannel(channel)
        }
    }

    companion object {
        val FetchDataChannel = "PlageApplication"
    }
}