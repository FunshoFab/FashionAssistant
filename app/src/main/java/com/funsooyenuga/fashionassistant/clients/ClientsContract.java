package com.funsooyenuga.fashionassistant.clients;

import com.funsooyenuga.fashionassistant.data.Client;

import java.util.List;

/**
 * Created by FAB THE GREAT on 08/12/2016.
 */

public interface ClientsContract {

    interface View {

        void showClients(List<Client> clients);

        void showMeasurement(String clientId, String sex);

        void showAddClientUi();
    }

    interface Presenter {

        void start();

        void getMeasurement(String clientId, String sex);

        void addClient();
    }
}
