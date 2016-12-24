package com.funsooyenuga.fashionassistant.addclient;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.funsooyenuga.fashionassistant.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFemaleClientFragment extends Fragment {

    private TextView capTitle, topTitle, trouserTitle;
    private TextInputLayout capMeasurement;

    public AddFemaleClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_client, container, false);

        capTitle = (TextView) v.findViewById(R.id.tv_cap_title);
        capMeasurement = (TextInputLayout) v.findViewById(R.id.til_cap_base);
        topTitle = (TextView) v.findViewById(R.id.tv_top_title);
        trouserTitle = (TextView) v.findViewById(R.id.tv_trouser_title);

        capTitle.setVisibility(View.GONE);
        capMeasurement.setVisibility(View.GONE);
        topTitle.setText("TOP/GOWN");
        trouserTitle.setText("TROUSER/SKIRT");

        return v;
    }

}
