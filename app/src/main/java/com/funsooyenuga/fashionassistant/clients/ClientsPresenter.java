package com.funsooyenuga.fashionassistant.clients;

import android.support.annotation.NonNull;

import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.source.ClientsDataSource.GetClientsCallback;
import com.funsooyenuga.fashionassistant.util.Injection;

import java.util.List;

/**
 * Created by FAB THE GREAT on 08/12/2016.
 */

public class ClientsPresenter implements ClientsContract.Presenter {

    private ClientsContract.View clientsView;

    public ClientsPresenter(@NonNull ClientsContract.View clientsView) {
        this.clientsView = clientsView;
    }

    @Override
    public void loadClients() {
        Injection.provideClientsDbApiImpl().getAllClients(new GetClientsCallback() {
            @Override
            public void onClientsLoaded(List<Client> clients) {
                clientsView.showClients(clients);
            }
        });
    }

    @Override
    public void getMeasurement(String clientId) {
        clientsView.showMeasurement(clientId);
    }

    @Override
    public void addClient() {
        clientsView.showAddClientUi();
    }
}
