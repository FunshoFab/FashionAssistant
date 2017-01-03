package com.funsooyenuga.fashionassistant.data.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.source.ClientDbSchema.ClientInfoTable;
import com.funsooyenuga.fashionassistant.util.HelperMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton that serves as the data source.
 */

public class ClientsDataSourceImpl2 implements ClientDataSource {

    private static final String TAG = "ClientsDataSourceImpl";
    private static ClientsDataSourceImpl2 instance;

    private ClientDbHelper dbHelper;

    private SQLiteDatabase db;

    private ClientsDataSourceImpl2(Context context) {
        dbHelper = new ClientDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static ClientsDataSourceImpl2 getInstance(Context context) {
        if (instance == null) {
            instance = new ClientsDataSourceImpl2(context);
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
                    Client client = HelperMethods.cursorToClient(cursor, new Client());
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
    public void saveClient(Client client) {
        ContentValues values = HelperMethods.clientToContentValues(client);
        try {
            db.insert(ClientInfoTable.TABLE_NAME, null, values);
        } catch (SQLiteException e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void updateClient(Client client) {

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
    public void toggleDelivered(Boolean delivered) {

    }
}
