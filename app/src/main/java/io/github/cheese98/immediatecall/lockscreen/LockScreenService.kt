package io.github.cheese98.immediatecall.lockscreen

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import io.github.cheese98.immediatecall.util.NotificationChannelOreo
import io.github.cheese98.immediatecall.util.receiver.ScreenReceiver

class LockScreenService : Service(){

    private val screenReceiver: ScreenReceiver? = ScreenReceiver(this)

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            var mBuilder : NotificationCompat.Builder = NotificationCompat.Builder(this@LockScreenService, NotificationChannelOreo.getNotificationId())
            //NotificationCompat.Builder(this@LockScreenService, "")
            startForeground(LockScreenApplication.notificationId, mBuilder.build())
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(screenReceiver, filter)

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        unregisterReceiver(screenReceiver)
    }
    fun startLockScreenActivity() {
        startActivity(LockScreenUtils.newIntent(this@LockScreenService))
    }
}