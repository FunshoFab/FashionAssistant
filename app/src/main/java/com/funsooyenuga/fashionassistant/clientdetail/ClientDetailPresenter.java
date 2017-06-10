package com.funsooyenuga.fashionassistant.clientdetail;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.loaders.ClientLoader;
import com.funsooyenuga.fashionassistant.data.source.ClientDataSource;

/**
 * Created by FAB THE GREAT on 19/12/2016.
 */

public class ClientDetailPresenter implements ClientDetailContract.Presenter,
        LoaderManager.LoaderCallbacks<Client> {

    private String clientId;

    private Client client; // keep a reference to the client in order to cancel a
    // notification when a client with a pending job is deleted

    private static final int LOADER_ID = 3;

    private ClientDetailContract.View clientDetailView;

    private ClientDataSource repository;

    private Loader<Client> loader;

    private LoaderManager loaderManager;

    private boolean clientLoaded;

    public ClientDetailPresenter(String clientId, ClientDetailContract.View clientDetailView, ClientLoader loader,
                                 LoaderManager loaderManager, ClientDataSource repository) {
        this.clientId = clientId;
        this.clientDetailView = clientDetailView;
        this.repository = repository;
        this.loader = loader;
        this.loaderManager = loaderManager;
    }

    @Override
    public void start() {
        clientLoaded = false;
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    @Override
    public void deleteClient() {
        cancelNotification();
        repository.deleteClient(clientId);
        clientDetailView.returnToClientsList();
    }

    private void cancelNotification() {
        if (client.isPending()) {
            clientDetailView.cancelNotification(client);
        }
    }

    //LOADER

    @Override
    public Loader<Client> onCreateLoader(int id, Bundle args) {
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Client> loader, Client data) {
        //onLoadFinished gets called twice as a result of a bug in the Loader API so we check if it
        //has not been previously called before delivering the result to the view
        if (data != null && !clientLoaded) {
            client = data;
            clientDetailView.showDetails(data);
            clientLoaded = true;
        }
    }

    @Override
    public void onLoaderReset(Loader<Client> loader) {

    }
}
