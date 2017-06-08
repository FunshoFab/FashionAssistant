package com.funsooyenuga.fashionassistant.notification;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by FAB THE GREAT on 06/06/2017.
 */

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (getResultCode() != Activity.RESULT_OK) {
        }
    }
}
