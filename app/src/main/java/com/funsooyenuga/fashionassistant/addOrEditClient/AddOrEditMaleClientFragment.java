package com.funsooyenuga.fashionassistant.addOrEditClient;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.loaders.ClientLoader;
import com.funsooyenuga.fashionassistant.data.source.ClientDataSource;
import com.funsooyenuga.fashionassistant.util.Injection;
import com.funsooyenuga.fashionassistant.util.Util;

import java.util.Date;


public class AddOrEditMaleClientFragment extends Fragment
        implements AddOrEditClientContract.View {

    public static final String TAG = "MaleFragment";

    public static final int RC_DATE_DIALOG = 1;

    private static final String ARG_CLIENT_ID = "arg_client_id";

    private String clientId;

    private String formattedDate;

    private AddOrEditClientContract.Presenter presenter;

    //TextView
    private TextView capTitle;
    //Client info
    private EditText name, phoneNumber, addInfo;
    private Button deliveryDate;
    //Cap and Top
    private EditText capBase, shoulder, chest, sleeve, cuff, topLength;
    //Trouser
    private EditText waist, thigh, trouserLength, bottom;
    //TextInputLayout
    private TextInputLayout tilChest, tilCapBase, tilName;

    private boolean dataOkay;

    private FloatingActionButton fab;

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

        ClientLoader loader = new ClientLoader(getActivity().getApplicationContext(), clientId);
        ClientDataSource repository = Injection.provideClientsRepository(getActivity().getApplicationContext());

        presenter = new AddOrEditClientPresenter(clientId, this, loader, getLoaderManager(), repository);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        boolean visible = isVisibleToUser && isResumed();
        initFab(visible);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
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
        View view = inflater.inflate(R.layout.fragment_add_client, container, false);

        initWidgets(view);
        unhideWidgets();
        initFab(getUserVisibleHint());

        Log.d(TAG, "onCreateView()");

        return view;
    }

    private void initWidgets(View v) {
        //TextView
        capTitle = (TextView) v.findViewById(R.id.tv_cap_title);
        //Client info
        name = (EditText) v.findViewById(R.id.et_client_name);
        phoneNumber = (EditText) v.findViewById(R.id.et_client_phone_number);
        addInfo  = (EditText) v.findViewById(R.id.et_additional_info);
        deliveryDate = (Button) v.findViewById(R.id.delivery_date_button);
        deliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

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

    private void showDateDialog() {
        DateDialogFragment dateDialog = new DateDialogFragment();
        dateDialog.setTargetFragment(this, RC_DATE_DIALOG);
        dateDialog.show(getFragmentManager(), TAG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_DATE_DIALOG && resultCode == Activity.RESULT_OK) {
            Date date = (Date) data.getSerializableExtra(DateDialogFragment.EXTRA_DATE);

            formattedDate = Util.formatDate(date);
            deliveryDate.setText(formattedDate);
        }
    }

    private void unhideWidgets() {
        capTitle.setVisibility(View.VISIBLE);
        tilCapBase.setVisibility(View.VISIBLE);
        tilChest.setVisibility(View.VISIBLE);
    }

    private void initFab(boolean visible) {
        if (visible) {
            fab = (FloatingActionButton) getActivity().findViewById(R.id.done);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getUserVisibleHint()) {
                        Client client = fetchClientDetail();
                        if (dataOkay) {
                            presenter.saveClient(client);
                        }
                    }
                }
            });
        } else {
            fab = null;
        }
    }

    @Override
    public void result() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setClientDetails(Client client) {
        //Client info
        name.setText(client.getName());
        phoneNumber.setText(client.getPhoneNumber());
        addInfo.setText(client.getAddInfo());
        if (client.getDeliveryDate() != null) {
            formattedDate = Util.formatDate(client.getDeliveryDate());
            deliveryDate.setText(formattedDate);
        }

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

        clientId = client.getId();
    }

    /**
     * Gets the data in the widgets and puts them in a Client object.
     * @return Client
     */
    private Client fetchClientDetail() {
        Client c = new Client();
        //Client Info
        c.setName(setName(name.getText().toString().trim()));
        c.setPhoneNumber(phoneNumber.getText().toString());
        c.setAddInfo(addInfo.getText().toString());
        c.setSex("m");
        c.setReceivedDate(new Date());
        if (formattedDate != null) {
            c.setDeliveryDate(Util.stringToDate(formattedDate));
        }

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

        if (clientId != null) {
            c.setId(clientId);
        }

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
