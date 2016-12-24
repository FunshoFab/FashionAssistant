package com.funsooyenuga.fashionassistant.addclient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.funsooyenuga.fashionassistant.R;


public class AddMaleClientFragment extends Fragment {


    public AddMaleClientFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new AddMaleClientFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_client, container, false);
    }

}
