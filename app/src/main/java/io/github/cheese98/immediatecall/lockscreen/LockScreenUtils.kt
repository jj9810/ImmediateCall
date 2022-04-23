package io.github.cheese98.immediatecall.lockscreen

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startForegroundService

/*
app\src\main\java\com\lockscreen\hanmo\lockscreenkotlinexample\lockscreen\util\LockScreen.kt
 */

object LockScreenUtils {
    fun newIntent(context: Context?) : Intent {
        return Intent(context, LockScreenActivity::class.java)
            .apply {
                //addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                //addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            }
    }

    fun active() {
        LockScreenApplication.instance?.applicationContext?.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(Intent(this, LockScreenService::class.java))
            } else {
                startService(Intent(this, LockScreenService::class.java))
            }
        }
    }


    fun deActivate() {
        LockScreenApplication.instance?.applicationContext?.run {
            stopService(Intent(this, LockScreenService::class.java))
        }
    }

    fun getLockScreenStatus() : Boolean {
        val lockScreenPreferences = LockScreenApplication.instance?.applicationContext?.run {
            getSharedPreferences("LockScreenStatus", Context.MODE_PRIVATE)
        }

        return lockScreenPreferences?.getBoolean("LockScreenStatus", false)!!
    }

    val isActive: Boolean
        get() = LockScreenApplication.instance?.applicationContext?.let {
            isMyServiceRunning(LockScreenService::class.java)
        } ?: kotlin.run {
            false
        }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = LockScreenApplication.instance?.applicationContext?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}