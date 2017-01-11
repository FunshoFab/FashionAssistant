package com.funsooyenuga.fashionassistant.addOrEditClient;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
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


public class AddOrEditMaleClientFragment extends Fragment
        implements AddOrEditClientContract.View {

    private static final String ARG_CLIENT_ID = "arg_client_id";

    private String clientId;

    private AddOrEditClientContract.Presenter presenter;

    //TextView
    private TextView capTitle;
    //Client info
    private EditText name, phoneNumber, addInfo;
    //Cap and Top
    private EditText capBase, shoulder, chest, sleeve, cuff, topLength;
    //Trouser
    private EditText waist, thigh, trouserLength, bottom;
    //TextInputLayout
    private TextInputLayout tilChest, tilCapBase, tilName;

    private boolean dataOkay;

    public AddOrEditMaleClientFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(@Nullable String clientId) {
        AddOrEditMaleClientFragment fragment = new AddOrEditMaleClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CLIENT_ID, clientId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clientId = getArguments().getString(ARG_CLIENT_ID);

        ClientLoader loader = new ClientLoader(getActivity().getApplicationContext(), null);
        ClientDataSource repository = Injection.provideClientsRepository(getActivity().getApplicationContext());

        presenter = new AddOrEditClientPresenter(clientId, this, loader, getLoaderManager(), repository);
    }

    /**
     * Shares the same layout as {@link AddOrEditFemaleClientFragment}. It modifies the layout by changing
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
        tilName = (TextInputLayout) v.findViewById(R.id.til_client_name);
    }

    private void unhideWidgets() {
        capTitle.setVisibility(View.VISIBLE);
        tilCapBase.setVisibility(View.VISIBLE);
        tilChest.setVisibility(View.VISIBLE);
    }

    @Override
    public void returnToClientList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setClientDetails(Client client) {
        //Client info
        name.setText(client.getName().toString());
        phoneNumber.setText(client.getPhoneNumber().toString());
        addInfo.setText(client.getAddInfo().toString());

        //Measurement - top
        capBase.setText(String.valueOf(client.getCapBase()));
        shoulder.setText(String.valueOf(client.getShoulder()));
        chest.setText(String.valueOf(client.getChestOrBust()));
        sleeve.setText(String.valueOf(client.getLongOrShortSleeve()));
        cuff.setText(String.valueOf(client.getCuffOrRoundSleeve()));
        topLength.setText(String.valueOf(client.getTopOrGownLength()));

        //Trouser
        waist.setText(String.valueOf(client.getWaist()));
        thigh.setText(String.valueOf(client.getThigh()));
        trouserLength.setText(String.valueOf(client.getTrouserLength()));
        bottom.setText(String.valueOf(client.getBottom()));
    }

    private Client fetchClientDetail() {
        Client c = new Client();
        //Client Info
        c.setName(setName(name.getText().toString().trim()));
        c.setPhoneNumber(phoneNumber.getText().toString());
        c.setAddInfo(addInfo.getText().toString());
        c.setSex("m");
        c.setReceivedDate(new Date());

        //Measurement - Top
        c.setCapBase(setValue(capBase.getText().toString()));
        c.setShoulder(setValue(shoulder.getText().toString()));
        c.setChestOrBust(setValue(chest.getText().toString()));
        c.setLongOrShortSleeve(setValue(sleeve.getText().toString()));
        c.setCuffOrRoundSleeve(setValue(cuff.getText().toString()));
        c.setTopOrGownLength(setValue(topLength.getText().toString()));
        //Trouser
        c.setWaist(setValue(waist.getText().toString()));
        c.setThigh(setValue(thigh.getText().toString()));
        c.setTrouserLength(setValue(trouserLength.getText().toString()));
        c.setBottom(setValue(bottom.getText().toString()));

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
