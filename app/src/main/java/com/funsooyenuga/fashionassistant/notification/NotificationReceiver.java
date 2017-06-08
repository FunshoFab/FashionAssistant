package com.funsooyenuga.fashionassistant.notification;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

/**
 * Created by FAB THE GREAT on 06/06/2017.
 */

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (getResultCode() == Activity.RESULT_OK) {
            Notification notification = intent.getParcelableExtra(NotificationService.EXTRA_NOTIFICATION);
            int notificationId = intent.getIntExtra(NotificationService.EXTRA_NOTIFICATION_ID, 0);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(notificationId, notification);
        }
    }
}
