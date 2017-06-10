package com.funsooyenuga.fashionassistant.clients;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.loaders.ClientsLoader;
import com.funsooyenuga.fashionassistant.data.source.ClientDataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by FAB THE GREAT on 08/12/2016.
 */

public class ClientsPresenter implements ClientsContract.Presenter,
        LoaderManager.LoaderCallbacks<List<Client>> {

    private ClientsContract.View clientsView;

    private ClientsLoader loader;

    private final int LOADER_ID = 1;

    private ClientDataSource repository;

    private LoaderManager loaderManager;

    private List<Client> clients;

    private ClientsFilterType filter;

    public ClientsPresenter(ClientsContract.View clientsView, ClientsLoader loader,
                            LoaderManager loaderManager, ClientDataSource repository,
                            ClientsFilterType filter) {
        this.clientsView = clientsView;
        this.loader = loader;
        this.loaderManager = loaderManager;
        this.repository = repository;
        this.filter = filter;
    }

    @Override
    public void start() {
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    @Override
    public void getMeasurement(String clientId, String sex, String name) {
        clientsView.showMeasurement(clientId, sex, name);
    }

    @Override
    public void addClient() {
        clientsView.showAddClientUi();
    }

    @Override
    public Loader<List<Client>> onCreateLoader(int id, Bundle args) {
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<Client>> loader, List<Client> data) {
        clients = data;
        filterClients(filter);
    }

    @Override
    public void onLoaderReset(Loader<List<Client>> loader) {

    }

    @Override
    public void filterClients(@NonNull ClientsFilterType filter) {
        this.filter = filter;
        List<Client> filteredClients = new ArrayList<>();

        if (clients != null) {
            for (final Client client : clients) {
                switch (filter) {
                    case PENDING_JOBS:
                        if (client.isPending()) {
                            filteredClients.add(client);
                            Collections.sort(filteredClients, new Comparator<Client>() {
                                @Override
                                public int compare(Client o1, Client o2) {
                                    return Client.sortByDate(o1, o2);
                                }
                            });
                        }
                        break;

                    case MEASUREMENTS:
                        if (!client.isPending()) {
                            filteredClients.add(client);
                            Collections.sort(filteredClients, new Comparator<Client>() {
                                @Override
                                public int compare(Client o1, Client o2) {
                                    return Client.sortByName(o1, o2);
                                }
                            });
                        }
                        break;

                    default:
                        filteredClients = null;
                }
            }
        }
        showFilteredClients(filteredClients);
    }

    private void showFilteredClients(List<Client> filteredClients) {
        if (filteredClients.isEmpty()) {
            showEmptyClients();
        } else {
            clientsView.showClients(filteredClients, filter);
        }
    }

    private void showEmptyClients() {
        clientsView.showNoDataUI(filter);
    }

    @Override
    public void setDelivered(String clientId) {
        cancelNotification(clientId);
        repository.setDelivered(clientId, true);
        clientsView.showDeliveredMessage();
    }

    private void cancelNotification(String clientId) {
        // Temporary hack to get the client in order to cancel the notification
        for (Client client : clients) {
            if (client.getId().equals(clientId)) {
                clientsView.cancelNotification(client);
            }
        }
    }
}
