package com.funsooyenuga.fashionassistant.addOrEditClient;

import com.funsooyenuga.fashionassistant.data.Client;

/**
 * Created by FAB THE GREAT on 07/01/2017.
 */

public interface AddOrEditClientContract {

    interface View {

        void setClientDetails(Client client);

        void setNotification(Client client);

        void result(boolean isPending);
    }

    interface Presenter {

        void start();

        void saveClient(Client client);
    }
}
