package com.funsooyenuga.fashionassistant.clients;

import com.funsooyenuga.fashionassistant.data.Client;

import java.util.List;

/**
 * Created by FAB THE GREAT on 08/12/2016.
 */

public interface ClientsContract {

    interface View {

        void showClients(List<Client> clients, ClientsFilterType filter);

        void showMeasurement(String clientId, String sex, String name);

        void showAddClientUi();

        void showDeliveredMessage();

        void cancelNotification(Client client);

        void showNoDataUI(ClientsFilterType filter);
    }

    interface Presenter {

        void start();

        void getMeasurement(String clientId, String sex, String name);

        void setDelivered(String clientId);

        void addClient();

        void filterClients(ClientsFilterType filter);
    }
}
