package com.funsooyenuga.fashionassistant.util;

import com.funsooyenuga.fashionassistant.data.source.ClientsDataSourceImpl;

/**
 * Created by FAB THE GREAT on 09/12/2016.
 */

public class Injection {
    public static ClientsDataSourceImpl provideClientsDbApiImpl() {
        return new ClientsDataSourceImpl();
    }
}
