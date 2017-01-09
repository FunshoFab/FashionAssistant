package com.funsooyenuga.fashionassistant.data.source;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable;
import com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable;

/**
 * Created by FAB THE GREAT on 07/12/2016.
 */

public class ClientDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "FashionAssistant.db";
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
            createClientTable(db);
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
                + MeasurementTable.TROUSER_LENGTH + ", "
                + MeasurementTable.CUFF_OR_ROUND_SLEEVE + ", "
                + MeasurementTable.LONG_OR_SHORT_SLEEVE + ", "
                + MeasurementTable.SHOULDER + ", "
                + MeasurementTable.THIGH + ", "
                + MeasurementTable.HIGH_WAIST + ", "
                + MeasurementTable.HIPS + ", "
                + MeasurementTable.KNEE_LENGTH + ", "
                + MeasurementTable.TOP_OR_GOWN_LENGTH + ", "
                + MeasurementTable.WAIST + ", ");
    }

    private void createClientTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ClientInfoTable.TABLE_NAME + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ClientInfoTable.CLIENT_ID + ", "
                + ClientInfoTable.CLIENT_NAME + ", "
                + ClientInfoTable.CLIENT_PHONE_NUMBER + ", "
                + ClientInfoTable.CLIENT_SEX + ", "
                + ClientInfoTable.DELIVERY_DATE + ", "
                + ClientInfoTable.RECEIVED_DATE + ", "
                + ClientInfoTable.DELIVERED + ", "
                + ClientInfoTable.ADD_INFO + ", "

                + MeasurementTable.MEASUREMENT_ID + ", "
                + MeasurementTable.CAP_BASE + ", "
                + MeasurementTable.BOTTOM + ", "
                + MeasurementTable.CHEST_OR_BUST + ", "
                + MeasurementTable.HALF_LENGTH + ", "
                + MeasurementTable.TROUSER_LENGTH + ", "
                + MeasurementTable.CUFF_OR_ROUND_SLEEVE + ", "
                + MeasurementTable.LONG_OR_SHORT_SLEEVE + ", "
                + MeasurementTable.SHOULDER + ", "
                + MeasurementTable.THIGH + ", "
                + MeasurementTable.HIGH_WAIST + ", "
                + MeasurementTable.HIPS + ", "
                + MeasurementTable.KNEE_LENGTH + ", "
                + MeasurementTable.TOP_OR_GOWN_LENGTH + ", "
                + MeasurementTable.WAIST + ")"
        );
    }
}
