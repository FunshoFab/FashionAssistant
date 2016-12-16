package com.funsooyenuga.fashionassistant.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.funsooyenuga.fashionassistant.data.ClientDbSchema.ClientInfoTable;
import com.funsooyenuga.fashionassistant.data.ClientDbSchema.MeasurementTable;

/**
 * Created by FAB THE GREAT on 07/12/2016.
 */

public class ClientDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Fashion assistant";
    private static final int DB_VERSION = 1;

    public ClientDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDB(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void updateDB(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            createClientInfoTable(db);
            createMeasurementTable(db);
        }
    }

    private void createMeasurementTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MeasurementTable.TABLE_NAME + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MeasurementTable.MEASUREMENT_ID + ", "
                + MeasurementTable.CAP_BASE + ", "
                + MeasurementTable.BOTTOM + ", "
                + MeasurementTable.CHEST_OR_BUST + ", "
                + MeasurementTable.HALF_LENGTH + ", "
                + MeasurementTable.LENGTH + ", "
                + MeasurementTable.LONG_SLEEVE + ", "
                + MeasurementTable.ROUND_SLEEVE + ", "
                + MeasurementTable.SHORT_SLEEVE + ", "
                + MeasurementTable.SHOULDER + ", "
                + MeasurementTable.THIGH + ", "
                + MeasurementTable.TOP_OR_GOWN_LENGTH + ", "
                + MeasurementTable.WAIST_OR_HIPS + ", ");
    }

    private void createClientInfoTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ClientInfoTable.TABLE_NAME + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ClientInfoTable.CLIENT_ID + ", "
                + ClientInfoTable.CLIENT_NAME + ", "
                + ClientInfoTable.CLIENT_PHONE_NUMBER + ", "
                + ClientInfoTable.CLIENT_SEX + ", "
                + ClientInfoTable.DUE_DATE + ", "
                + ClientInfoTable.DELIVERED);
    }
}
