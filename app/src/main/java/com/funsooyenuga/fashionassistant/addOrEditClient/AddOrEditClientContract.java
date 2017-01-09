package com.funsooyenuga.fashionassistant.addOrEditClient;

import com.funsooyenuga.fashionassistant.data.Client;

/**
 * Created by FAB THE GREAT on 07/01/2017.
 */

public interface AddOrEditClientContract {

    interface View {

        void setClientDetails(Client client);

        void returnToClientList();
    }

    interface Presenter {

        void start();

        void saveClient(Client client);
    }
}
