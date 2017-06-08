package com.funsooyenuga.fashionassistant.notification;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * A dynamic receiver that Cancels a notification if its subclass is visible.
 */
public class CancelNotifFragment extends Fragment {

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Cancels the Notification so the NotificationReceiver wont show the notification
            setResultCode(Activity.RESULT_CANCELED);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(NotificationService.ACTION_SHOW_NOTIFICATION);

        getActivity().registerReceiver(broadcastReceiver, filter, NotificationService.BROADCAST_PERMISSION, null);
    }

    @Override
    public void onStop() {
        super.onStop();

        getActivity().unregisterReceiver(broadcastReceiver);
    }
}
