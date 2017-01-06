package com.funsooyenuga.fashionassistant.data.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.source.ClientsRepository;
import com.funsooyenuga.fashionassistant.util.Injection;

import java.util.List;

/**
 * Created by FAB THE GREAT on 06/01/2017.
 */

public class ClientsLoader extends AsyncTaskLoader<List<Client>>
        implements ClientsRepository.ClientsRepositoryObserver{

    private ClientsRepository repository;

    public ClientsLoader(Context context) {
        super(context);
        repository = Injection.provideClientsRepository(context);
    }

    @Override
    public List<Client> loadInBackground() {
        return repository.getClients();
    }

    @Override
    public void deliverResult(List<Client> data) {
        if (isReset()) {
            return;
        }

        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (repository.cacheAvailable()) {
            deliverResult(repository.getClients());
        }
        repository.addContentObserver(this);

        if (takeContentChanged() || !repository.cacheAvailable()) {
            forceLoad();
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
