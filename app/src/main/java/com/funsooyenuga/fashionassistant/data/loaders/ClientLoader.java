package com.funsooyenuga.fashionassistant.data.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.source.ClientsRepository;
import com.funsooyenuga.fashionassistant.util.Injection;

/**
 * Created by FAB THE GREAT on 05/01/2017.
 */

public class ClientLoader extends AsyncTaskLoader<Client>
        implements ClientsRepository.ClientsRepositoryObserver {

    private final String clientId;

    private ClientsRepository repository;

    public ClientLoader(Context context, String clientId) {
        super(context);
        this.clientId = clientId;
        repository = Injection.provideClientsRepository(context);
    }

    @Override
    public Client loadInBackground() {
        return repository.getClient(clientId);
    }

    @Override
    protected void onStartLoading() {

        //If there is data in the repository, deliver it immediately
        if (repository.cacheAvailable()) {
            deliverResult(repository.getClient(clientId));
        }

        //start monitoring this data source
        repository.addContentObserver(this);

        //If the content has changed or the repository is unavailable, load fresh data
        if (takeContentChanged() || !repository.cacheAvailable()) {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(Client client) {
        if (isReset()) {
            return;
        }

        if (isStarted()) {
            super.deliverResult(client);
        }
    }

    @Override
    protected void onReset() {
        onStopLoading();
        repository.removeContentObservers(this);
    }

    @Override
    public void onDataChange() {
        if (isStarted()) {
            forceLoad();
        }
    }
}
