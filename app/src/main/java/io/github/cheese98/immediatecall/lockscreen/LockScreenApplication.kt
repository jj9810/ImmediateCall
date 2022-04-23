package io.github.cheese98.immediatecall.lockscreen

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class LockScreenApplication : MultiDexApplication() {
    init{
        instance = this@LockScreenApplication
    }
    companion object{
        var instance : LockScreenApplication? = null

        const val notificationId : Int = 1

        fun active() {
            instance?.applicationContext?.run {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(Intent(this, LockScreenService::class.java))
                } else {
                    startService(Intent(this, LockScreenService::class.java))
                }
            }
        }

        fun deActivate() {
            instance?.applicationContext?.run {
                stopService(Intent(this, LockScreenService::class.java))
            }
        }

        fun getLockScreenStatus() : Boolean {
            val lockScreenPreferences = instance?.applicationContext?.run {
                getSharedPreferences("LockScreenStatus", Context.MODE_PRIVATE)
            }

            return lockScreenPreferences?.getBoolean("LockScreenStatus", false)!!
        }

        val isActive: Boolean
            get() = instance?.applicationContext?.let {
                isMyServiceRunning(LockScreenService::class.java)
            } ?: kotlin.run {
                false
            }

        private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
            val manager = instance?.applicationContext?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
                if (serviceClass.name == service.service.className) {
                    return true
                }
            }
            return false
        }

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this@LockScreenApplication)
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }
}