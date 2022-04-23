package io.github.cheese98.immediatecall.util;

import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

object NotificationChannelOreo {
    private val NOTIFICATION_CHANNEL_ID = "ImmediateCall ID"
    private val NOTIFICATION_CHANNEL_NAME = "ImmediateCall CHANEL"
    //private val CHANNEL_DESCRIPTION = "This is LockScreen Example CHANEL"

    fun getNotificationId(): String {
        return NOTIFICATION_CHANNEL_ID
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotification(context : Context?) {
        val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, android.app.NotificationManager.IMPORTANCE_HIGH)

        notificationChannel.enableLights(false)
        notificationChannel.enableVibration(false)

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }

}