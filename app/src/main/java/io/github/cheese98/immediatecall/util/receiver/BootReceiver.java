package io.github.cheese98.immediatecall.util.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        if(action.equals(Intent.ACTION_BOOT_COMPLETED)){
            //활성화
        }
    }
}
