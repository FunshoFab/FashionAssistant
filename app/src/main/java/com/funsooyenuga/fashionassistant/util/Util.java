package com.funsooyenuga.fashionassistant.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable;
import com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable;

import java.text.ParseException;
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
        if (client.getDeliveryDate() != null) {
            cv.put(ClientInfoTable.DELIVERY_DATE, client.getDeliveryDate().getTime());
        }
        cv.put(ClientInfoTable.RECEIVED_DATE, client.getReceivedDate().getTime());
        cv.put(ClientInfoTable.CLIENT_ID, client.getId());
        cv.put(ClientInfoTable.ADD_INFO, client.getAddInfo());
        cv.put(ClientInfoTable.DELIVERED, client.isDelivered());

        //MEASUREMENT
        //Cap
        cv.put(MeasurementTable.CAP_BASE, client.getCapBase());
        //Top or Gown
        cv.put(MeasurementTable.CHEST_OR_BUST, client.getChestOrBust());
        cv.put(MeasurementTable.LONG_SLEEVE, client.getLongSleeve());
        cv.put(MeasurementTable.SHORT_SLEEVE, client.getShortSleeve());
        cv.put(MeasurementTable.ROUND_SLEEVE, client.getRoundSleeve());
        cv.put(MeasurementTable.CUFF, client.getCuff());
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

    public static Client cursorToClient(Cursor c, Client client) {

        //CLIENT INFO
        client.setId(c.getString(c.getColumnIndex(ClientInfoTable.CLIENT_ID)));
        client.setName(c.getString(c.getColumnIndex(ClientInfoTable.CLIENT_NAME)));
        client.setPhoneNumber(c.getString(c.getColumnIndex(ClientInfoTable.CLIENT_PHONE_NUMBER)));
        client.setSex(c.getString(c.getColumnIndex(ClientInfoTable.CLIENT_SEX)));

        client.setDeliveryDate(c.getLong(c.getColumnIndex(ClientInfoTable.DELIVERY_DATE)));
        client.setReceivedDate(new Date(c.getLong(c.getColumnIndex(ClientInfoTable.RECEIVED_DATE))));
        client.setAddInfo(c.getString(c.getColumnIndex(ClientInfoTable.ADD_INFO)));
        client.setDelivered(c.getInt(c.getColumnIndex(ClientInfoTable.DELIVERED)) == 1);

        //MEASUREMENT
        //Cap
        client.setCapBase(c.getDouble(c.getColumnIndex(MeasurementTable.CAP_BASE)));
        //Top
        client.setShoulder(c.getDouble(c.getColumnIndex(MeasurementTable.SHOULDER)));
        client.setChestOrBust(c.getDouble(c.getColumnIndex(MeasurementTable.CHEST_OR_BUST)));
        client.setLongSleeve(c.getDouble(c.getColumnIndex(MeasurementTable.LONG_SLEEVE)));
        client.setShortSleeve(c.getDouble(c.getColumnIndex(MeasurementTable.SHORT_SLEEVE)));
        client.setRoundSleeve(c.getDouble(c.getColumnIndex(MeasurementTable.ROUND_SLEEVE)));
        client.setTopOrGownLength(c.getDouble(c.getColumnIndex(MeasurementTable.TOP_OR_GOWN_LENGTH)));
        client.setCuff(c.getDouble(c.getColumnIndex(MeasurementTable.CUFF)));
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

    public static void hostFragment(FragmentManager fm, int resourceId, Fragment fragment, String tag) {
        if (fragment != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(resourceId, fragment, tag)
                    .commit();
        }
    }

    public static String formatDate(@NonNull Date date) {
        String pattern = "EEE, d MMM yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date stringToDate(@NonNull String formattedDate) {
        String pattern = "EEE, d MMM yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        Date date = null;
        try {
            date = dateFormat.parse(formattedDate);
        } catch (ParseException e) {
            Log.d("Util", e.toString());
        }
        return date;
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

    public static void setDynamicListHeight(ListView listview) {
        ListAdapter listAdapter = listview.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int height = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(listview.getWidth(), MeasureSpec.UNSPECIFIED);

        for (int i=0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listview);
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = height + (listview.getDividerHeight() * (listAdapter.getCount() - 1));
        listview.setLayoutParams(params);
        listview.requestLayout();
        Log.d("Util", "height is: " + height);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
