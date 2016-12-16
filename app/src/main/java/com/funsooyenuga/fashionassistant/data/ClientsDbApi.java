package com.funsooyenuga.fashionassistant.data;

import java.util.List;

/**
 * Created by FAB THE GREAT on 08/12/2016.
 */

public interface ClientsDbApi {

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
