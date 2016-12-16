package com.funsooyenuga.fashionassistant.clientdetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.funsooyenuga.fashionassistant.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientDetailFragment extends Fragment {

    private static final String ARGUMENT_CLIENT_ID = "CLIENT_ID";

    public static ClientDetailFragment newInstance(String clientId) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_CLIENT_ID, clientId);

        ClientDetailFragment fragment = new ClientDetailFragment();
        fragment.setArguments(args);

        return fragment;
    }


    public ClientDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_detail, container, false);
    }

}
