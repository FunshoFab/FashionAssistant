package com.funsooyenuga.fashionassistant.notification;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.clients.ClientsActivity;
import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.source.ClientsRepository;
import com.funsooyenuga.fashionassistant.settings.SettingsActivity;
import com.funsooyenuga.fashionassistant.util.DateUtil;
import com.funsooyenuga.fashionassistant.util.Injection;

import java.util.Date;
import java.util.List;

public class NotificationService extends IntentService {

    public static final String TAG = "NotificationService";

    private static final String EXTRA_CLIENT_NAME = "extra_client_name";
    private static final String EXTRA_DELIVERY_DATE = "extra_delivery_date";
    public static final String EXTRA_NOTIFICATION_ID = "extra_notification_id";
    public static final String EXTRA_NOTIFICATION = "notification";

    public static final String ACTION_SHOW_NOTIFICATION = "com.funsooyenuga.fashionassistant.SHOW_NOTIFICATION";

    public static final String BROADCAST_PERMISSION = "com.funsooyenuga.fashionassistant.PRIVATE";

    public static Intent newIntent(Context context, String clientName, String deliveryDate, int notificationId) {
        Intent intent = new Intent(context, NotificationService.class);

        intent.putExtra(EXTRA_CLIENT_NAME, clientName);
        intent.putExtra(EXTRA_NOTIFICATION_ID, notificationId);
        intent.putExtra(EXTRA_DELIVERY_DATE, deliveryDate);

        return intent;
    }

    public NotificationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String clientName = intent.getStringExtra(EXTRA_CLIENT_NAME);
        String date = intent.getStringExtra(EXTRA_DELIVERY_DATE);
        int notificationId = intent.getIntExtra(EXTRA_NOTIFICATION_ID, 0);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, ClientsActivity.newIntent(this),0);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Pending job")
                .setContentText(clientName + "'s delivery is on " + date)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        Intent notifIntent = new Intent(ACTION_SHOW_NOTIFICATION);
        notifIntent.putExtra(EXTRA_NOTIFICATION, notification);
        notifIntent.putExtra(EXTRA_NOTIFICATION_ID, notificationId);

        sendOrderedBroadcast(notifIntent, BROADCAST_PERMISSION, null, null, Activity.RESULT_OK, null, null);
    }

    /**
     * Sets or cancels a notification
     *
     * @param context
     * @param flag if true, it sets the notification, if false, it cancels the notification
     */
    public static void setNotification(Context context, Client client, boolean flag) {
        Date deliveryDate = client.getDeliveryDate();
        if (!notificationIsEnabled(context)) {
            return;
        }
        String date = DateUtil.formatToRelativeDate(deliveryDate);
        String name = client.getName();
        int notificationId = client.getNotificationId();

        Intent intent = NotificationService.newIntent(context, name, date, notificationId);

        PendingIntent pendingIntent = PendingIntent.getService(context, notificationId,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        if (flag) {
            long alarmTime = client.getAlarmTime();
            if (alarmTime == 0) {
                // An alarm has not been previously set
                alarmTime = setAlarmTime(deliveryDate.getTime(), getInterval(context));
                client.setAlarmTime(alarmTime);
                saveAlarm(context, client);
            }
            alarmManager.set(AlarmManager.RTC, alarmTime, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    private static boolean notificationIsEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
                SettingsActivity.KEY_PREF_NOTIFICATION_STATUS, true);
    }

    private static int getInterval(Context context) {
        return Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getString(
                SettingsActivity.KEY_PREF_INTERVAL, ""));
    }

    private static long setAlarmTime(long deliveryDate, int interval) {
        long alarmTime;
        long currentTime = System.currentTimeMillis();
        int daysBeforeDelivery = (int) (deliveryDate - currentTime) / DateUtil.ONE_DAY_IN_MILLI_SECS;

        if (daysBeforeDelivery <= interval) {
            /*
                Example: Interval is 5 days before delivery
                 1. User registers a new job to be delivered in 3 days :D
                 2. User registers a new job to be delivered in 5 days

                Solution: Set Notification to a day after the Job is added
            */
            if (daysBeforeDelivery <= DateUtil.ONE_DAY) {
                // Delivery is less than one day, set Notification 12 hours from delivery
                alarmTime = deliveryDate - DateUtil.TWELVE_HOURS;
            } else {
                alarmTime = currentTime + DateUtil.ONE_DAY_IN_MILLI_SECS;
            }
        }
        else {
            // Notify user at the interval set in Notification setting
            if (interval <= DateUtil.ONE_DAY) {
                // set alarm for 12 hours before delivery
                alarmTime = deliveryDate - DateUtil.TWELVE_HOURS;
            } else {
                alarmTime = deliveryDate - DateUtil.ONE_DAY_IN_MILLI_SECS * interval;
            }
        }
        return alarmTime;
    }

    /**
     * Sets alarm after they might have been cleared on phone reboot
     *
     * @param context
     * @param resetAlarmTime If this is true, it would change the alarm time previously
     *                       set. Only set this to true when an event like notification interval
     *                       change occurs.
     */
    public static void resetAlarms(Context context, boolean resetAlarmTime) {
        if (notificationIsEnabled(context)) {
            ClientsRepository repository = Injection.provideClientsRepository(context);

            List<Client> clients = repository.getPendingClients();
            if (!clients.isEmpty()) {
                for (Client client : clients) {
                    if (resetAlarmTime) {
                        client.setAlarmTime(0);
                    }
                    setNotification(context, client, true);
                }
            }
        }
    }

    /**
     * Saves an alarm to the database in order to restart them when phone reboots
     *
     * @param context
     * @param client
     */
    private static void saveAlarm(Context context, Client client) {
        ClientsRepository repository = Injection.provideClientsRepository(context);
        repository.updateClient(client);
    }
}
