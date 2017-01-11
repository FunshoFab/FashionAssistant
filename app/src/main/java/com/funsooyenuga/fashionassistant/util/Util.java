package com.funsooyenuga.fashionassistant.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable;
import com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by FAB THE GREAT on 07/12/2016.
 */

public class Util {

    public static ContentValues clientToContentValues(Client client) {
        ContentValues cv = new ContentValues();

        //CLIENT INFO
        cv.put(ClientInfoTable.CLIENT_NAME, client.getName());
        cv.put(ClientInfoTable.CLIENT_PHONE_NUMBER, client.getPhoneNumber());
        cv.put(ClientInfoTable.CLIENT_SEX, client.getSex());
//        cv.put(ClientInfoTable.DELIVERY_DATE, client.getDeliveryDate().getTime());
        cv.put(ClientInfoTable.RECEIVED_DATE, client.getReceivedDate().getTime());
    //    cv.put(ClientInfoTable.DELIVERED, client.getDelivered() ? 1 : 0);
        cv.put(ClientInfoTable.CLIENT_ID, client.getId().toString());
        cv.put(ClientInfoTable.ADD_INFO, client.getAddInfo());

        //MEASUREMENT
        //Cap
        cv.put(MeasurementTable.CAP_BASE, client.getCapBase());
        //Top or Gown
        cv.put(MeasurementTable.CHEST_OR_BUST, client.getChestOrBust());
        cv.put(MeasurementTable.LONG_OR_SHORT_SLEEVE, client.getLongOrShortSleeve());
        cv.put(MeasurementTable.CUFF_OR_ROUND_SLEEVE, client.getCuffOrRoundSleeve());
        cv.put(MeasurementTable.SHOULDER, client.getShoulder());
        cv.put(MeasurementTable.TOP_OR_GOWN_LENGTH, client.getTopOrGownLength());
        cv.put(MeasurementTable.HALF_LENGTH, client.getHalfLength());
        cv.put(MeasurementTable.KNEE_LENGTH, client.getKneeLength());
        cv.put(MeasurementTable.HIGH_WAIST, client.getHighWaist());

        //Trouser
        cv.put(MeasurementTable.THIGH, client.getThigh());
        cv.put(MeasurementTable.WAIST, client.getWaist());
        cv.put(MeasurementTable.BOTTOM, client.getBottom());
        cv.put(MeasurementTable.TROUSER_LENGTH, client.getTrouserLength());
        cv.put(MeasurementTable.HIPS, client.getHips());

        return cv;
    }

    public static void hostFragment(FragmentManager fm, int resourceId, Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(resourceId, fragment)
                .commit();
    }

    public static Client cursorToClient(Cursor c, Client client) {

        //CLIENT INFO
        client.setId(c.getString(c.getColumnIndex(ClientInfoTable.CLIENT_ID)));
        client.setName(c.getString(c.getColumnIndex(ClientInfoTable.CLIENT_NAME)));
        client.setPhoneNumber(c.getString(c.getColumnIndex(ClientInfoTable.CLIENT_PHONE_NUMBER)));
        client.setSex(c.getString(c.getColumnIndex(ClientInfoTable.CLIENT_SEX)));
        client.setDeliveryDate(new Date(c.getLong(c.getColumnIndex(ClientInfoTable.DELIVERY_DATE))));
        client.setReceivedDate(new Date(c.getLong(c.getColumnIndex(ClientInfoTable.RECEIVED_DATE))));
        client.setAddInfo(c.getString(c.getColumnIndex(ClientInfoTable.ADD_INFO)));

        //MEASUREMENT
        //Cap
        client.setCapBase(c.getDouble(c.getColumnIndex(MeasurementTable.CAP_BASE)));
        //Top
        client.setShoulder(c.getDouble(c.getColumnIndex(MeasurementTable.SHOULDER)));
        client.setChestOrBust(c.getDouble(c.getColumnIndex(MeasurementTable.CHEST_OR_BUST)));
        client.setLongOrShortSleeve(c.getDouble(c.getColumnIndex(MeasurementTable.LONG_OR_SHORT_SLEEVE)));
        client.setTopOrGownLength(c.getDouble(c.getColumnIndex(MeasurementTable.TOP_OR_GOWN_LENGTH)));
        client.setCuffOrRoundSleeve(c.getDouble(c.getColumnIndex(MeasurementTable.CUFF_OR_ROUND_SLEEVE)));
        client.setHighWaist(c.getDouble(c.getColumnIndex(MeasurementTable.HIGH_WAIST)));
        client.setKneeLength(c.getDouble(c.getColumnIndex(MeasurementTable.KNEE_LENGTH)));
        client.setHalfLength(c.getDouble(c.getColumnIndex(MeasurementTable.HALF_LENGTH)));

        //Trouser
        client.setWaist(c.getDouble(c.getColumnIndex(MeasurementTable.WAIST)));
        client.setTrouserLength(c.getDouble(c.getColumnIndex(MeasurementTable.TROUSER_LENGTH)));
        client.setThigh(c.getDouble(c.getColumnIndex(MeasurementTable.THIGH)));
        client.setBottom(c.getDouble(c.getColumnIndex(MeasurementTable.BOTTOM)));
        client.setHips(c.getDouble(c.getColumnIndex(MeasurementTable.HIPS)));

        return client;
    }

    public static String formatDate(Date date) {
        String pattern = "EEE, d MMM yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

}
