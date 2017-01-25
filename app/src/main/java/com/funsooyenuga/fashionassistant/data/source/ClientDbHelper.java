package com.funsooyenuga.fashionassistant.data.source;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable;
import com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.MeasurementTable;

import java.util.ArrayList;

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

                + MeasurementTable.CAP_BASE + ", "
                + MeasurementTable.BOTTOM + ", "
                + MeasurementTable.CHEST_OR_BUST + ", "
                + MeasurementTable.HALF_LENGTH + ", "
                + MeasurementTable.TROUSER_LENGTH + ", "
                + MeasurementTable.CUFF + ", "
                + MeasurementTable.ROUND_SLEEVE + ", "
                + MeasurementTable.SHORT_SLEEVE + ", "
                + MeasurementTable.LONG_SLEEVE + ", "
                + MeasurementTable.SHOULDER + ", "
                + MeasurementTable.THIGH + ", "
                + MeasurementTable.HIGH_WAIST + ", "
                + MeasurementTable.HIPS + ", "
                + MeasurementTable.KNEE_LENGTH + ", "
                + MeasurementTable.TOP_OR_GOWN_LENGTH + ", "
                + MeasurementTable.WAIST + ")"
        );
    }

    //DbManager methods
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}
