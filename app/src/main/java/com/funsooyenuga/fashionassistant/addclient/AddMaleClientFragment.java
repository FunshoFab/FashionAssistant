package com.funsooyenuga.fashionassistant.addclient;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.funsooyenuga.fashionassistant.R;


public class AddMaleClientFragment extends Fragment {
    //TextView
    private TextView capTitle;
    //Client info
    private EditText name, phoneNumber, addInfo;
    //Cap and Top
    private EditText capBase, shoulder, chest, sleeve, cuff, topLength;
    //Trouser
    private EditText waist, thigh, trouserLength, bottom;
    //TextInputLayout
    private TextInputLayout tilChest, tilCapBase;


    public AddMaleClientFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new AddMaleClientFragment();
    }

    /**
     * Shares the same layout as {@link AddFemaleClientFragment}. It modifies the layout by changing
     * the title of some TextViews and un-hiding some widgets peculiar to this Fragment alone.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_client, container, false);

        initWidgets(v);
        unhideWidgets();

        return v;
    }

    private void initWidgets(View v) {
        //TextView
        capTitle = (TextView) v.findViewById(R.id.tv_cap_title);
        //Client info
        name = (EditText) v.findViewById(R.id.et_client_name);
        phoneNumber = (EditText) v.findViewById(R.id.et_client_phone_number);
        addInfo  = (EditText) v.findViewById(R.id.et_additional_info);

        //Cap and Top
        capBase = (EditText) v.findViewById(R.id.et_cap_base);
        shoulder = (EditText) v.findViewById(R.id.et_shoulder);
        chest = (EditText) v.findViewById(R.id.et_chest);
        sleeve = (EditText) v.findViewById(R.id.et_sleeve);
        cuff = (EditText) v.findViewById(R.id.et_cuff_rsl);
        topLength = (EditText) v.findViewById(R.id.et_top_length);

        //Trouser
        waist = (EditText) v.findViewById(R.id.et_waist);
        thigh = (EditText) v.findViewById(R.id.et_thigh);
        trouserLength = (EditText) v.findViewById(R.id.et_trouser_length);
        bottom = (EditText) v.findViewById(R.id.et_bottom);

        //TextInputLayout
        tilCapBase = (TextInputLayout) v.findViewById(R.id.til_cap_base);
        tilChest = (TextInputLayout) v.findViewById(R.id.til_chest);
    }

    private void unhideWidgets() {
        capTitle.setVisibility(View.VISIBLE);
        tilCapBase.setVisibility(View.VISIBLE);
        tilChest.setVisibility(View.VISIBLE);
    }
}
