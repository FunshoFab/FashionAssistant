package com.funsooyenuga.fashionassistant.clients;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.loaders.ClientsLoader;
import com.funsooyenuga.fashionassistant.data.source.ClientDataSource;

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

    public ClientsPresenter(ClientsContract.View clientsView, ClientsLoader loader,
                            LoaderManager loaderManager, ClientDataSource repository) {
        this.clientsView = clientsView;
        this.loader = loader;
        this.loaderManager = loaderManager;
        this.repository = repository;
    }

    @Override
    public void start() {
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    @Override
    public void getMeasurement(String clientId) {
        clientsView.showMeasurement(clientId);
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
        clientsView.showClients(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Client>> loader) {

    }
}
