package com.funsooyenuga.fashionassistant.notification;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.funsooyenuga.fashionassistant.data.Client;

public class NotificationService extends IntentService {

    public static final String TAG = "NotificationService";
    private static final String EXTRA_CLIENT_NAME = "extra_client_name";
    private static final String EXTRA_NOTIFICATION_ID = "extra_notification_id";
    public static final String ACTION_SHOW_NOTIFICATION = "com.funsooyenuga.fashionassistant.SHOW_NOTIFICATION";
    public static final String BROADCAST_PERMISSION = "com.funsooyenuga.fashionassistant.PRIVATE";

    public static Intent newIntent(Context context, String clientName, int notificationId) {
        Intent intent = new Intent(context, NotificationService.class);
        intent.putExtra(EXTRA_CLIENT_NAME, clientName);
        intent.putExtra(EXTRA_NOTIFICATION_ID, notificationId);

        return intent;
    }

    public NotificationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendOrderedBroadcast(new Intent(ACTION_SHOW_NOTIFICATION), BROADCAST_PERMISSION, null, null,
                Activity.RESULT_OK, null, null);
    }

    /**
     * Sets or cancels a notification
     *
     * @param context
     * @param flag if true, it sets the notification, if false, it cancels the notification
     */
    public static void setNotification(Context context, Client client, boolean flag) {
        Intent intent = NotificationService.newIntent(context, client.getName(), client.getNotificationId());
        PendingIntent pendingIntent = PendingIntent.getService(context, client.getNotificationId(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        if (flag) {
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis()+5000, pendingIntent);

        } else {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }
}
