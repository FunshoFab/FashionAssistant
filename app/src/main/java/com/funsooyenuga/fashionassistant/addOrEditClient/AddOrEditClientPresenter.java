package com.funsooyenuga.fashionassistant.addOrEditClient;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.loaders.ClientLoader;
import com.funsooyenuga.fashionassistant.data.source.ClientDataSource;

/**
 * Created by FAB THE GREAT on 08/01/2017.
 */

public class AddOrEditClientPresenter implements AddOrEditClientContract.Presenter,
        LoaderManager.LoaderCallbacks<Client> {

    private static final String TAG = "AEPresenter";
    private String clientId;

    private static final int LOADER_ID = 2;

    private AddOrEditClientContract.View addClientView;

    private ClientDataSource repository;

    private Loader<Client> loader;

    private LoaderManager loaderManager;

    AddOrEditClientPresenter(String clientId, AddOrEditClientContract.View addClientView, ClientLoader loader,
                                    LoaderManager loaderManager, ClientDataSource repository) {
        this.clientId = clientId;
        this.addClientView = addClientView;
        this.repository = repository;
        this.loader = loader;
        this.loaderManager = loaderManager;
    }

    @Override
    public void start() {
        if (!isNewClient()) {
            loaderManager.initLoader(LOADER_ID, null, this);
        }
    }

    @Override
    public void saveClient(Client client) {
        if (isNewClient()) {
            repository.saveClient(client);
        } else {
            repository.updateClient(client);
        }
        addClientView.result(client.isPending());
    }

    private boolean isNewClient() {
        return clientId == null;
    }

    //LOADER

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Client> loader, Client data) {
        addClientView.setClientDetails(data);
    }

    @Override
    public void onLoaderReset(Loader<Client> loader) {

    }
}
