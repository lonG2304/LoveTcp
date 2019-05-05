package com.parking.park;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DeviceReBootReceiver extends BroadcastReceiver {
    public DeviceReBootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        }
    }

}
