package io.github.cheese98.immediatecall.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PackageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        if(action.equals(intent.ACTION_MY_PACKAGE_REPLACED)){
            // 활성화
        }
    }
}
