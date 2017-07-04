package com.funsooyenuga.fashionassistant.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.notification.NotificationService;

public class SettingsActivity extends AppCompatActivity {

    public static final String KEY_PREF_INTERVAL = "pref_notification_interval";
    public static final String KEY_PREF_NOTIFICATION_STATUS = "pref_notification_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragment {

        private SharedPreferences.OnSharedPreferenceChangeListener listener =
                new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                        if (key.equals(KEY_PREF_INTERVAL)) {
                            Preference intervalPref = findPreference(key);
                            int newInterval = Integer.valueOf(sharedPreferences.getString(key, ""));
                            intervalPref.setSummary(getResources().getQuantityString(R.plurals.pref_interval_summary, newInterval, newInterval));

                            NotificationService.resetAlarms(getActivity(), true);
                        }
                    }
                };

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.pref_settings);
            setSummary();
        }


        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(listener);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(listener);
        }

        private void setSummary() {
            Preference intervalPref = findPreference(KEY_PREF_INTERVAL);
            int newInterval = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(KEY_PREF_INTERVAL, ""));
            intervalPref.setSummary(getResources().getQuantityString(R.plurals.pref_interval_summary, newInterval, newInterval));
        }
    }
}
