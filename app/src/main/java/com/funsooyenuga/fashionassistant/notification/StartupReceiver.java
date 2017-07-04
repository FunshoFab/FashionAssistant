package com.funsooyenuga.fashionassistant.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Created by FAB THE GREAT on 01/07/2017.
 */

public class StartupReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationService.resetAlarms(context, false);
    }
}
