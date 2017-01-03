package com.funsooyenuga.fashionassistant.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable;
import com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable;

/**
 * Created by FAB THE GREAT on 07/12/2016.
 */

public class HelperMethods {

    public static ContentValues clientToContentValues(Client client) {
        ContentValues cv = new ContentValues();

        //CLIENT INFO
        cv.put(ClientInfoTable.CLIENT_NAME, client.getName());
        cv.put(ClientInfoTable.CLIENT_PHONE_NUMBER, client.getPhoneNumber());
        cv.put(ClientInfoTable.CLIENT_SEX, client.getSex());
        cv.put(ClientInfoTable.DUE_DATE, client.getDueDate());
        cv.put(ClientInfoTable.DELIVERED, client.getDelivered() ? 1 : 0);
        cv.put(ClientInfoTable.CLIENT_ID, client.getId().toString());

        //MEASUREMENT
        cv.put(MeasurementTable.MEASUREMENT_ID, client.getId().toString());

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
        client.setDueDate(c.getString(c.getColumnIndex(ClientInfoTable.DUE_DATE)));

        //MEASUREMENT
        //Cap
        client.setCapBase(c.getString(c.getColumnIndex(MeasurementTable.CAP_BASE)));
        //Top
        client.setShoulder(c.getString(c.getColumnIndex(MeasurementTable.SHOULDER)));
        client.setChestOrBust(c.getString(c.getColumnIndex(MeasurementTable.CHEST_OR_BUST)));
        client.setLongOrShortSleeve(c.getString(c.getColumnIndex(MeasurementTable.LONG_OR_SHORT_SLEEVE)));
        client.setTopOrGownLength(c.getString(c.getColumnIndex(MeasurementTable.TOP_OR_GOWN_LENGTH)));
        client.setCuffOrRoundSleeve(c.getString(c.getColumnIndex(MeasurementTable.CUFF_OR_ROUND_SLEEVE)));
        client.setHighWaist(c.getString(c.getColumnIndex(MeasurementTable.HIGH_WAIST)));
        client.setKneeLength(c.getString(c.getColumnIndex(MeasurementTable.KNEE_LENGTH)));
        client.setHalfLength(c.getString(c.getColumnIndex(MeasurementTable.HALF_LENGTH)));

        //Trouser
        client.setWaist(c.getString(c.getColumnIndex(MeasurementTable.WAIST)));
        client.setTrouserLength(c.getString(c.getColumnIndex(MeasurementTable.TROUSER_LENGTH)));
        client.setThigh(c.getString(c.getColumnIndex(MeasurementTable.THIGH)));
        client.setBottom(c.getString(c.getColumnIndex(MeasurementTable.BOTTOM)));
        client.setHips(c.getString(c.getColumnIndex(MeasurementTable.HIPS)));

        return client;
    }
}
