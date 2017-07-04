package com.funsooyenuga.fashionassistant.data.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable;
import com.funsooyenuga.fashionassistant.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.*;

/**
 * A singleton that serves as the data source.
 */

public class ClientsDataSourceImpl implements ClientDataSource {

    private static final String TAG = "ClientsDataSourceImpl";

    private static ClientsDataSourceImpl instance;

    private ClientDbHelper dbHelper;

    private SQLiteDatabase db;

    private ClientsDataSourceImpl(Context context) {
        dbHelper = new ClientDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static ClientsDataSourceImpl getInstance(Context context) {
        if (instance == null) {
            instance = new ClientsDataSourceImpl(context);
        }
        return instance;
    }

    @Override
    public void clearDeliveredJobs() {

    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();

        try {
            Cursor cursor = db.query(
                    ClientInfoTable.TABLE_NAME, null, null, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Client client = Util.cursorToClient(cursor, new Client());
                    clients.add(client);
                }
                cursor.close();
            }
        } catch(SQLiteException e) {
            Log.e(TAG, e.toString());
        }

        return clients;
    }

    @Override
    public List<Client> getPendingClients() {
        List<Client> clients = new ArrayList<>();

        String selection = "CAST(" + Notification.ALARM_EXECUTED + " AS TEXT) = ?" +
                           " AND CAST(" + ClientInfoTable.DELIVERED + " AS TEXT) = ?";
        String[] selectionArgs = {Integer.toString(0), Integer.toString(0)};

        try {
            Cursor cursor = db.query(
                    ClientInfoTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Client client = Util.cursorToClient(cursor, new Client());
                    clients.add(client);
                }
                cursor.close();
            }
        } catch(SQLiteException e) {
            Log.e(TAG, e.toString());
        }
        Log.d(TAG, "Clients: " + clients.size());
        // Log.d(TAG, "Selection: " + selection);
        // Log.d(TAG, "SelectionArgs[0]: " + selectionArgs[0]);
        return clients;
    }

    @Override
    public void saveClient(Client client) {
        ContentValues values = Util.clientToContentValues(client);
        try {
            db.insert(ClientInfoTable.TABLE_NAME, null, values);
        } catch (SQLiteException e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void updateClient(Client client) {
        ContentValues values = Util.clientToContentValues(client);
        String whereClause = ClientInfoTable.CLIENT_ID + " = ?";
        String[] whereArgs = {client.getId()};

        try {
            db.update(ClientInfoTable.TABLE_NAME, values, whereClause, whereArgs);
        } catch (SQLiteException e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void deleteClient(String clientId) {
        String whereClause = ClientInfoTable.CLIENT_ID + " = ?";
        String[] whereArgs = {clientId};

        try {
            db.delete(ClientInfoTable.TABLE_NAME, whereClause, whereArgs);
        } catch (SQLiteException e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void setDelivered(String clientId, boolean delivered) {
        ContentValues cv = new ContentValues();
        cv.put(ClientInfoTable.DELIVERED, delivered);
        cv.put(ClientInfoTable.DELIVERY_DATE, 0);

        String whereClause = ClientInfoTable.CLIENT_ID + " = ?";
        String[] whereArgs = {clientId};

        try {
            db.update(ClientInfoTable.TABLE_NAME, cv, whereClause, whereArgs);
        } catch (SQLiteException e) {
            Log.e(TAG, e.toString());
        }
    }
}
