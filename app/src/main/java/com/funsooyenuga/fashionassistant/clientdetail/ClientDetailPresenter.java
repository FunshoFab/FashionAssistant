package com.funsooyenuga.fashionassistant.clientdetail;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.loaders.ClientLoader;
import com.funsooyenuga.fashionassistant.data.source.ClientDataSource;

/**
 * Created by FAB THE GREAT on 19/12/2016.
 */

public class ClientDetailPresenter implements ClientDetailContract.Presenter,
        LoaderManager.LoaderCallbacks<Client> {

    private String clientId;

    private static final int LOADER_ID = 3;

    private ClientDetailContract.View clientDetailView;

    private ClientDataSource repository;

    private Loader<Client> loader;

    private LoaderManager loaderManager;

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
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    //LOADER

    @Override
    public Loader<Client> onCreateLoader(int id, Bundle args) {
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Client> loader, Client data) {
        clientDetailView.showDetails(data);
    }

    @Override
    public void onLoaderReset(Loader<Client> loader) {

    }
}
