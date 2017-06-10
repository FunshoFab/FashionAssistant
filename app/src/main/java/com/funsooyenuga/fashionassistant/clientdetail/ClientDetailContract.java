package com.funsooyenuga.fashionassistant.clientdetail;

import com.funsooyenuga.fashionassistant.data.Client;

/**
 * Created by FAB THE GREAT on 10/12/2016.
 */

public interface ClientDetailContract {

    interface View {

        void showDetails(Client client);

        void cancelNotification(Client client);

        void returnToClientsList();
    }

    interface Presenter {

        void start();

        void deleteClient();
    }
}
