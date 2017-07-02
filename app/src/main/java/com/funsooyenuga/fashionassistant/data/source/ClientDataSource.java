package com.funsooyenuga.fashionassistant.data.source;

import com.funsooyenuga.fashionassistant.data.Client;

import java.util.List;

/**
 * Created by FAB THE GREAT on 30/12/2016.
 */

public interface ClientDataSource {

    List<Client> getClients();

    List<Client> getPendingClients();

    void saveClient(Client client);

    void updateClient(Client client);

    void deleteClient(String clientId);

    void clearDeliveredJobs();

    void setDelivered(String clientId, boolean delivered);

}
