package com.funsooyenuga.fashionassistant.data.source;

import com.funsooyenuga.fashionassistant.data.Client;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FAB THE GREAT on 03/01/2017.
 */

public class ClientsRepository implements ClientDataSource {

    private static ClientsRepository instance;

    private final ClientsDataSourceImpl2 dataSource;

    private Map<String, Client> cachedClients;

    private boolean cacheAvailable;

    private ClientsRepository(ClientsDataSourceImpl2 dataSource) {
        this.dataSource = dataSource;
    }

    public static ClientsRepository getInstance(ClientsDataSourceImpl2 dataSource) {
        if (instance == null) {
            instance = new ClientsRepository(dataSource);
        }
        return instance;
    }

    @Override
    public void clearDeliveredJobs() {

    }

    @Override
    public List<Client> getClients() {
        if (cacheAvailable()) {
            return getCachedClients();
        }

        List<Client> clients = dataSource.getClients();
        cacheClients(clients);
        return clients;
    }

    private void cacheClients(List<Client> clients) {
        cachedClients = new LinkedHashMap<>();

        for (Client client : clients) {
            cachedClients.put(client.getId(), client);
        }
        cacheAvailable = true;
    }

    private List<Client> getCachedClients() {
        return new ArrayList<>(cachedClients.values());
    }

    private boolean cacheAvailable() {
        return cacheAvailable && cachedClients != null;
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
}
