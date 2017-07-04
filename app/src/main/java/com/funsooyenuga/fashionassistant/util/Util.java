package com.funsooyenuga.fashionassistant.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MenuItem;
import android.widget.EditText;

import com.funsooyenuga.fashionassistant.data.Client;

import java.util.Date;

import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable.ADD_INFO;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable.CLIENT_ID;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable.CLIENT_NAME;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable.CLIENT_PHONE_NUMBER;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable.CLIENT_SEX;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable.DELIVERED;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable.DELIVERY_DATE;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable.RECEIVED_DATE;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.BOTTOM;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.CAP_BASE;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.CHEST_OR_BUST;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.CUFF;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.HALF_LENGTH;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.HIGH_WAIST;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.HIPS;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.KNEE_LENGTH;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.LONG_SLEEVE;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.ROUND_SLEEVE;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.SHORT_SLEEVE;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.SHOULDER;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.THIGH;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.TOP_OR_GOWN_LENGTH;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.TROUSER_LENGTH;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable.WAIST;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.Notification.ALARM_EXECUTED;
import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.Notification.ALARM_TIME;

/**
 * Created by FAB THE GREAT on 07/12/2016.
 */

public class Util {

    public static ContentValues clientToContentValues(Client client) {
        ContentValues cv = new ContentValues();

        //CLIENT INFO
        cv.put(CLIENT_NAME, client.getName());
        cv.put(CLIENT_PHONE_NUMBER, client.getPhoneNumber());
        cv.put(CLIENT_SEX, client.getSex());
        cv.put(DELIVERY_DATE, client.getDeliveryDate() != null ? client.getDeliveryDate().getTime() : 0);
        cv.put(RECEIVED_DATE, client.getReceivedDate().getTime());
        cv.put(CLIENT_ID, client.getId());
        cv.put(ADD_INFO, client.getAddInfo());
        cv.put(DELIVERED, client.isDelivered());

        //MEASUREMENT
        //Cap
        cv.put(CAP_BASE, client.getCapBase());
        //Top or Gown
        cv.put(CHEST_OR_BUST, client.getChestOrBust());
        cv.put(LONG_SLEEVE, client.getLongSleeve());
        cv.put(SHORT_SLEEVE, client.getShortSleeve());
        cv.put(ROUND_SLEEVE, client.getRoundSleeve());
        cv.put(CUFF, client.getCuff());
        cv.put(SHOULDER, client.getShoulder());
        cv.put(TOP_OR_GOWN_LENGTH, client.getTopOrGownLength());
        cv.put(HALF_LENGTH, client.getHalfLength());
        cv.put(KNEE_LENGTH, client.getKneeLength());
        cv.put(HIGH_WAIST, client.getHighWaist());

        //Trouser
        cv.put(THIGH, client.getThigh());
        cv.put(WAIST, client.getWaist());
        cv.put(BOTTOM, client.getBottom());
        cv.put(TROUSER_LENGTH, client.getTrouserLength());
        cv.put(HIPS, client.getHips());

        // Notification
        cv.put(ALARM_TIME, client.getAlarmTime());
        cv.put(ALARM_EXECUTED, client.isAlarmExecuted());

        return cv;
    }

    public static Client cursorToClient(Cursor c, Client client) {

        // CLIENT INFO
        client.setId(c.getString(c.getColumnIndex(CLIENT_ID)));
        client.setName(c.getString(c.getColumnIndex(CLIENT_NAME)));
        client.setPhoneNumber(c.getString(c.getColumnIndex(CLIENT_PHONE_NUMBER)));
        client.setSex(c.getString(c.getColumnIndex(CLIENT_SEX)));

        client.setDeliveryDate(c.getLong(c.getColumnIndex(DELIVERY_DATE)));
        client.setReceivedDate(new Date(c.getLong(c.getColumnIndex(RECEIVED_DATE))));
        client.setAddInfo(c.getString(c.getColumnIndex(ADD_INFO)));
        client.setDelivered(c.getInt(c.getColumnIndex(DELIVERED)) == 1);

        // MEASUREMENT
        // Cap
        client.setCapBase(c.getDouble(c.getColumnIndex(CAP_BASE)));
        // Top
        client.setShoulder(c.getDouble(c.getColumnIndex(SHOULDER)));
        client.setChestOrBust(c.getDouble(c.getColumnIndex(CHEST_OR_BUST)));
        client.setLongSleeve(c.getDouble(c.getColumnIndex(LONG_SLEEVE)));
        client.setShortSleeve(c.getDouble(c.getColumnIndex(SHORT_SLEEVE)));
        client.setRoundSleeve(c.getDouble(c.getColumnIndex(ROUND_SLEEVE)));
        client.setTopOrGownLength(c.getDouble(c.getColumnIndex(TOP_OR_GOWN_LENGTH)));
        client.setCuff(c.getDouble(c.getColumnIndex(CUFF)));
        client.setHighWaist(c.getDouble(c.getColumnIndex(HIGH_WAIST)));
        client.setKneeLength(c.getDouble(c.getColumnIndex(KNEE_LENGTH)));
        client.setHalfLength(c.getDouble(c.getColumnIndex(HALF_LENGTH)));

        // Trouser
        client.setWaist(c.getDouble(c.getColumnIndex(WAIST)));
        client.setTrouserLength(c.getDouble(c.getColumnIndex(TROUSER_LENGTH)));
        client.setThigh(c.getDouble(c.getColumnIndex(THIGH)));
        client.setBottom(c.getDouble(c.getColumnIndex(BOTTOM)));
        client.setHips(c.getDouble(c.getColumnIndex(HIPS)));

        // Notification
        client.setAlarmExecuted(c.getInt(c.getColumnIndex(ALARM_EXECUTED)) == 1);
        client.setAlarmTime(c.getLong(c.getColumnIndex(ALARM_TIME)));

        return client;
    }

    public static void hostFragment(FragmentManager fm, int resourceId, Fragment fragment, String tag) {
        if (fragment != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(resourceId, fragment, tag)
                    .commit();
        }
    }

    public static void tintMenuIcon(Context context, MenuItem item, @ColorRes int color) {
        Drawable drawable = DrawableCompat.wrap(item.getIcon());
        DrawableCompat.setTint(drawable, ContextCompat.getColor(context, color));
        item.setIcon(drawable);
    }

    /**
     * Sets the value of an EditText if it is greater than 0.
     * @param editText
     * @param measurementValue
     */
    public static void set(EditText editText, double measurementValue) {
        if (measurementValue > 0) {
            editText.setText(String.valueOf(measurementValue));
        }
    }
}
