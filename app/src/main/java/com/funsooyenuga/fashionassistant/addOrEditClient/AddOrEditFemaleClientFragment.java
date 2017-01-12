package com.funsooyenuga.fashionassistant.addOrEditClient;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.loaders.ClientLoader;
import com.funsooyenuga.fashionassistant.data.source.ClientDataSource;
import com.funsooyenuga.fashionassistant.util.Injection;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddOrEditFemaleClientFragment extends Fragment
        implements AddOrEditClientContract.View {

    private static final String ARG_CLIENT_ID = "arg_client_id";

    private String clientId;

    private AddOrEditClientContract.Presenter presenter;

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

    private boolean dataOkay;

    public static Fragment newInstance(@Nullable String clientId) {
        AddOrEditFemaleClientFragment fragment = new AddOrEditFemaleClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CLIENT_ID, clientId);
        fragment.setArguments(args);

        return fragment;
    }

    public AddOrEditFemaleClientFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clientId = getArguments().getString(ARG_CLIENT_ID);

        ClientLoader loader = new ClientLoader(getActivity().getApplicationContext(), clientId);
        ClientDataSource repository = Injection.provideClientsRepository(getActivity().getApplicationContext());

        presenter = new AddOrEditClientPresenter(clientId, this, loader, getLoaderManager(), repository);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    /**
     * Shares the same layout as {@link AddOrEditMaleClientFragment}. It modifies the layout by changing
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

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client client = fetchClientDetail();
                if (dataOkay) {
                    presenter.saveClient(client);
                }
            }
        });


        return v;
    }

    private void initWidgets(View v) {
        //Titles
        top = (TextView) v.findViewById(R.id.tv_top_title);
        trouser = (TextView) v.findViewById(R.id.tv_trouser_title);

        //Client info
        name = (EditText) v.findViewById(R.id.et_client_name);
        name.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
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

    @Override
    public void returnToClientList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setClientDetails(Client client) {
        //Client info
        name.setText(client.getName());
        phoneNumber.setText(client.getPhoneNumber());
        addInfo.setText(client.getAddInfo());

        //Measurement - top
        shoulder.setText(String.valueOf(client.getShoulder()));
        bust.setText(String.valueOf(client.getChestOrBust()));
        sleeve.setText(String.valueOf(client.getLongOrShortSleeve()));
        cuff.setText(String.valueOf(client.getCuffOrRoundSleeve()));
        topLength.setText(String.valueOf(client.getTopOrGownLength()));
        halfLength.setText(String.valueOf(client.getHalfLength()));
        kneeLength.setText(String.valueOf(client.getKneeLength()));
        highWaist.setText(String.valueOf(client.getWaist()));

        //Trouser
        waist.setText(String.valueOf(client.getWaist()));
        thigh.setText(String.valueOf(client.getThigh()));
        trouserLength.setText(String.valueOf(client.getTrouserLength()));
        bottom.setText(String.valueOf(client.getBottom()));
        hips.setText(String.valueOf(client.getHips()));
    }

    private Client fetchClientDetail() {
        Client c = new Client();

        //Client Info
        c.setName(setName(name.getText().toString().trim()));
        c.setPhoneNumber(phoneNumber.getText().toString());
        c.setAddInfo(addInfo.getText().toString());
        c.setSex("f");
        c.setReceivedDate(new Date());

        //Measurement - Top/Gown
        c.setShoulder(setValue(shoulder.getText().toString()));
        c.setChestOrBust(setValue(bust.getText().toString()));
        c.setLongOrShortSleeve(setValue(sleeve.getText().toString()));
        c.setCuffOrRoundSleeve(setValue(cuff.getText().toString()));
        c.setTopOrGownLength(setValue(topLength.getText().toString()));
        c.setHalfLength(setValue(halfLength.getText().toString()));
        c.setKneeLength(setValue(kneeLength.getText().toString()));
        c.setHighWaist(setValue(highWaist.getText().toString()));

        //Trouser/skirt
        c.setWaist(setValue(waist.getText().toString()));
        c.setThigh(setValue(thigh.getText().toString()));
        c.setTrouserLength(setValue(trouserLength.getText().toString()));
        c.setBottom(setValue(bottom.getText().toString()));
        c.setHips(setValue(hips.getText().toString()));

        return c;
    }

    private String setName(String etName) {
        String trimmedName;
        if (etName.equals("")) {
            name.setError(getString(R.string.empty_name_error_message));
            dataOkay = false;
            trimmedName = null;
        } else {
            dataOkay = true;
            trimmedName = etName;
        }
        return trimmedName;
    }

    private double setValue(String value) {
        if (value.isEmpty())
            return 0;
        else
            return Double.valueOf(value);
    }
}