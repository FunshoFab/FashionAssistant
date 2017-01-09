package com.funsooyenuga.fashionassistant.data.source;

import com.funsooyenuga.fashionassistant.data.Client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by FAB THE GREAT on 08/12/2016.
 */

public class ClientsDataSourceImpl implements ClientDataSource {

    @Override
    public void clearDeliveredJobs() {

    }

    @Override
    public List<Client> getClients() {
        return fakeData();
    }

    @Override
    public void saveClient(Client client) {

    }

    @Override
    public void updateClient(Client client) {

    }

    @Override
    public void deleteClient(String clientId) {

    }

    @Override
    public void toggleDelivered(Boolean delivered) {

    }

    private static List<Client> fakeData() {
        final List<Client> fakeClients = new ArrayList<>();

        Client client1 = new Client();
        client1.setName("Funsho Fab");
        client1.setDeliveryDate(new Date());

        Client client2 = new Client();
        client2.setName("Lolla Oye");
        client2.setDeliveryDate(new Date());

        fakeClients.add(client1);
        fakeClients.add(client1);
        fakeClients.add(client1);
        fakeClients.add(client2);

        return fakeClients;
    }
}
