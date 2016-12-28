package com.funsooyenuga.fashionassistant.data.source;

import com.funsooyenuga.fashionassistant.data.Client;

import java.util.List;

/**
 * Created by FAB THE GREAT on 08/12/2016.
 */

public interface ClientsDataSource {

    interface GetClientsCallback {

        void onClientsLoaded(List<Client> client);
    }

    interface GetMeasurementCallback {

        void onClientLoaded(Client client);
    }

    void getAllClients(GetClientsCallback callback);

    void getMeasurement(String id, GetMeasurementCallback callback);

    void saveClient();
}
