package com.funsooyenuga.fashionassistant.util;

import android.content.Context;

import com.funsooyenuga.fashionassistant.data.source.ClientsDataSourceImpl2;
import com.funsooyenuga.fashionassistant.data.source.ClientsRepository;

/**
 * Created by FAB THE GREAT on 09/12/2016.
 */

public class Injection {
    public static ClientsRepository provideClientsRepository(Context context) {
        return ClientsRepository.getInstance(ClientsDataSourceImpl2.getInstance(context));
        //return ClientsRepository.getInstance(new ClientsDataSourceImpl());
    }
}
