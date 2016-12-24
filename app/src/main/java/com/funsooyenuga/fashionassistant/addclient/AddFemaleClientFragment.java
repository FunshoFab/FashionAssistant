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

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFemaleClientFragment extends Fragment {

    //TextView
    private TextView top, trouser;
    //Client info
    private EditText name, phoneNumber, addInfo;
    //Top
    private EditText shoulder, bust, sleeve, cuff, topLength, halfLength, kneeLength, highWaist;
    //Trouser
    private EditText waist, thigh, trouserLength, bottom, hips;
    //TextInputLayout
    private TextInputLayout tilBust, tilHalfLength, tilKneeLength, tilHighWaist, tilHips;


    public static Fragment newInstance() {
        return new AddFemaleClientFragment();
    }

    public AddFemaleClientFragment() {
        // Required empty public constructor
    }


    /**
     * Shares the same layout as {@link AddMaleClientFragment}. It modifies the layout by changing
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
        changeTitle();
        unhideWidgets();

        return v;
    }

    private void initWidgets(View v) {
        //Titles
        top = (TextView) v.findViewById(R.id.tv_top_title);
        trouser = (TextView) v.findViewById(R.id.tv_trouser_title);

        //Client info
        name = (EditText) v.findViewById(R.id.et_client_name);
        phoneNumber = (EditText) v.findViewById(R.id.et_client_phone_number);
        addInfo  = (EditText) v.findViewById(R.id.et_additional_info);

        //Top
        shoulder = (EditText) v.findViewById(R.id.et_shoulder);
        bust = (EditText) v.findViewById(R.id.et_bust);
        sleeve = (EditText) v.findViewById(R.id.et_sleeve);
        cuff = (EditText) v.findViewById(R.id.et_cuff_rsl);
        topLength = (EditText) v.findViewById(R.id.et_top_length);
        halfLength = (EditText) v.findViewById(R.id.et_half_length);
        kneeLength = (EditText) v.findViewById(R.id.et_knee_length);
        highWaist = (EditText) v.findViewById(R.id.et_high_waist);

        //Trouser
        waist = (EditText) v.findViewById(R.id.et_waist);
        thigh = (EditText) v.findViewById(R.id.et_thigh);
        trouserLength = (EditText) v.findViewById(R.id.et_trouser_length);
        bottom = (EditText) v.findViewById(R.id.et_bottom);
        hips = (EditText) v.findViewById(R.id.et_hips);

        //TextInputLayout
        tilBust = (TextInputLayout) v.findViewById(R.id.til_bust);
        tilHalfLength = (TextInputLayout) v.findViewById(R.id.til_half_length);
        tilHighWaist = (TextInputLayout) v.findViewById(R.id.til_high_waist);
        tilKneeLength = (TextInputLayout) v.findViewById(R.id.til_knee_length);
        tilHips = (TextInputLayout) v.findViewById(R.id.til_hips);
    }

    private void changeTitle() {
        top.setText(getResources().getString(R.string.top_or_gown_title));
        trouser.setText(getResources().getString(R.string.trouser_or_skirt_title));
    }

    private void unhideWidgets() {
        //Top
        tilBust.setVisibility(View.VISIBLE);
        tilHalfLength.setVisibility(View.VISIBLE);
        tilKneeLength.setVisibility(View.VISIBLE);
        tilHighWaist.setVisibility(View.VISIBLE);
        //Trouser
        tilHips.setVisibility(View.VISIBLE);
    }


}
