package com.funsooyenuga.fashionassistant.util;

import com.funsooyenuga.fashionassistant.data.ClientsDbApiImpl;

/**
 * Created by FAB THE GREAT on 09/12/2016.
 */

public class Injection {
    public static ClientsDbApiImpl provideClientsDbApiImpl() {
        return new ClientsDbApiImpl();
    }
}
