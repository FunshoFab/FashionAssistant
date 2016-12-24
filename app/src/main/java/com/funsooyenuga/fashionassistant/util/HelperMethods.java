package com.funsooyenuga.fashionassistant.util;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.ClientDbSchema;

/**
 * Created by FAB THE GREAT on 07/12/2016.
 */

public class HelperMethods {

    public static ContentValues getClientValues(SQLiteDatabase db, Client client) {
        ContentValues cv = new ContentValues();

        cv.put(ClientDbSchema.ClientInfoTable.CLIENT_NAME, client.getName());
        cv.put(ClientDbSchema.ClientInfoTable.CLIENT_PHONE_NUMBER, client.getPhoneNumber());
        cv.put(ClientDbSchema.ClientInfoTable.CLIENT_SEX, client.getSex());
        cv.put(ClientDbSchema.ClientInfoTable.DUE_DATE, client.getDueDate());
        cv.put(ClientDbSchema.ClientInfoTable.DELIVERED, client.getDelivered());
        cv.put(ClientDbSchema.ClientInfoTable.CLIENT_ID, client.getId().toString());

        return cv;
    }

    public static ContentValues getMeasurementValues(SQLiteDatabase db, Client client) {
        ContentValues cv = new ContentValues();

        cv.put(ClientDbSchema.MeasurementTable.CAP_BASE, client.getCap_base_m());
        cv.put(ClientDbSchema.MeasurementTable.BOTTOM, client.getBottom());
        cv.put(ClientDbSchema.MeasurementTable.CHEST_OR_BUST, client.getChestOrBust());
        cv.put(ClientDbSchema.MeasurementTable.HALF_LENGTH, client.getHalfLength_f());
        cv.put(ClientDbSchema.MeasurementTable.LENGTH, client.getLength());
        cv.put(ClientDbSchema.MeasurementTable.LONG_SLEEVE, client.getLong_or_short_sleeve());
        cv.put(ClientDbSchema.MeasurementTable.ROUND_SLEEVE, client.getCuff_or_round_sleeve());
        cv.put(ClientDbSchema.MeasurementTable.SHOULDER, client.getShoulder());
        cv.put(ClientDbSchema.MeasurementTable.THIGH, client.getThigh());
        cv.put(ClientDbSchema.MeasurementTable.TOP_OR_GOWN_LENGTH, client.getTopOrGownLength());
        cv.put(ClientDbSchema.MeasurementTable.WAIST_OR_HIPS, client.getWaist());
        cv.put(ClientDbSchema.MeasurementTable.MEASUREMENT_ID, client.getId().toString());

        return cv;
    }

    public static void hostFragment(FragmentManager fm, int resourceId, Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(resourceId, fragment)
                .commit();
    }

    public static void initWidgets(View v) {

    }

}
