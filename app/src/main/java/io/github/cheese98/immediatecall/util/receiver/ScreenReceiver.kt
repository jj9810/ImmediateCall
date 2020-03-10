package io.github.cheese98.immediatecall.util.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat.startActivity
import io.github.cheese98.immediatecall.lockscreen.LockScreenActivity
import io.github.cheese98.immediatecall.lockscreen.LockScreenService

class ScreenReceiver (private val lsService : LockScreenService) : BroadcastReceiver (){
    // 지연 초기화시 오류 발생으로 인해 수정
    private var tm: TelephonyManager? = null
    private var phoneState: Int? = null

    override fun onReceive(context: Context?, intent: Intent) {
        if(intent.action.equals(Intent.ACTION_SCREEN_OFF)){
            tm = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            tm?.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
        if(phoneState == TelephonyManager.CALL_STATE_IDLE){
            lsService.startLockScreenActivity()
        }
    }
    private val phoneListener = object : PhoneStateListener(){
        override fun onCallStateChanged(state: Int, phoneNumber: String?) {
            super.onCallStateChanged(state, phoneNumber)
            phoneState = state;
        }
    }
}