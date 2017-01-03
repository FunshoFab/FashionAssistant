package com.funsooyenuga.fashionassistant.data.source;

import com.funsooyenuga.fashionassistant.data.Client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by FAB THE GREAT on 08/12/2016.
 */

public class ClientsDataSourceImpl implements ClientsDataSource {


    @Override
    public void getAllClients(GetClientsCallback callback) {
        callback.onClientsLoaded(fakeData());

    }

    @Override
    public void getMeasurement(String id, GetMeasurementCallback callback) {

    }

    @Override
    public void saveClient() {

    }

    private static List<Client> fakeData() {
        final List<Client> fakeClients = new ArrayList<>();

        Client client1 = new Client();
        client1.setName("Funsho Fab");
        client1.setDueDate(new Date());

        Client client2 = new Client();
        client2.setName("Lolla Oye");
        client2.setDueDate(new Date());

        fakeClients.add(client1);
        fakeClients.add(client1);
        fakeClients.add(client1);
        fakeClients.add(client2);

        return fakeClients;
    }
}
