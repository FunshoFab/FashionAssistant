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
import com.funsooyenuga.fashionassistant.clients.ClientsFragment;
import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.loaders.ClientLoader;
import com.funsooyenuga.fashionassistant.data.source.ClientDataSource;
import com.funsooyenuga.fashionassistant.notification.NotificationService;
import com.funsooyenuga.fashionassistant.util.Injection;
import com.funsooyenuga.fashionassistant.util.Util;

import java.util.Date;

import static com.funsooyenuga.fashionassistant.util.Util.set;


public class AddOrEditClientFragment extends Fragment
        implements AddOrEditClientContract.View {

    public static final String TAG = "MaleFragment";
    private static final String ARG_CLIENT_ID = "argClientId";
    private static final String ARG_CLIENT_SEX = "argClientSex";

    public static final int RC_DATE_DIALOG = 1;

    private String clientId;
    private String sex;
    private String formattedDate;

    private AddOrEditClientContract.Presenter presenter;

    //TextView
    private TextView capTitle, top, trouser;
    //Client info
    private EditText name, phoneNumber, addInfo;
    private Button deliveryDate;
    //Cap and Top
    private EditText capBase, shoulder, chest, bust, longSleeve, cuff, topLength, roundSleeve,
            shortSleeve, halfLength, kneeLength, highWaist, hips;
    //Trouser
    private EditText waist, thigh, trouserLength, bottom;
    //TextInputLayout
    private TextInputLayout tilChest, tilCapBase, tilBust, tilHalfLength, tilKneeLength, tilHighWaist, tilHips;

    private boolean dataOkay;

    private FloatingActionButton fab;

    public AddOrEditClientFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(@Nullable String clientId, String sex) {
        AddOrEditClientFragment fragment = new AddOrEditClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CLIENT_ID, clientId);
        args.putString(ARG_CLIENT_SEX, sex);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clientId = getArguments().getString(ARG_CLIENT_ID);
        sex = getArguments().getString(ARG_CLIENT_SEX);

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_client, container, false);

        initWidgets(view);
        toggleWidgets();
        changeSubHead();
        initFab(getUserVisibleHint());

        Log.d(TAG, "onCreateView()");

        return view;
    }

    private void initWidgets(View v) {
        //Subhead
        capTitle = (TextView) v.findViewById(R.id.tv_cap_title);
        top = (TextView) v.findViewById(R.id.tv_top_title);
        trouser = (TextView) v.findViewById(R.id.tv_trouser_title);

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
        shoulder = (EditText) v.findViewById(R.id.et_shoulder);
        chest = (EditText) v.findViewById(R.id.et_chest);
        bust = (EditText) v.findViewById(R.id.et_bust);
        longSleeve = (EditText) v.findViewById(R.id.et_long_sleeve);
        shortSleeve = (EditText) v.findViewById(R.id.et_short_sleeve);
        roundSleeve = (EditText) v.findViewById(R.id.et_round_sleeve);
        cuff = (EditText) v.findViewById(R.id.et_cuff);
        topLength = (EditText) v.findViewById(R.id.et_top_length);
        //---Male specific
        capBase = (EditText) v.findViewById(R.id.et_cap_base);
        //---Female specific
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
        //---Male specific
        tilCapBase = (TextInputLayout) v.findViewById(R.id.til_cap_base);
        tilChest = (TextInputLayout) v.findViewById(R.id.til_chest);
        //---Female specific
        tilBust = (TextInputLayout) v.findViewById(R.id.til_bust);
        tilHalfLength = (TextInputLayout) v.findViewById(R.id.til_half_length);
        tilHighWaist = (TextInputLayout) v.findViewById(R.id.til_high_waist);
        tilKneeLength = (TextInputLayout) v.findViewById(R.id.til_knee_length);
        tilHips = (TextInputLayout) v.findViewById(R.id.til_hips);

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

    private void toggleWidgets() {
        if (isMale()) {
            toggleMaleWidgets(View.VISIBLE);
            toggleFemaleWidgets(View.GONE);
        } else {
            toggleMaleWidgets(View.GONE);
            toggleFemaleWidgets(View.VISIBLE);
        }
    }

    private void toggleMaleWidgets(int visibility) {
        capTitle.setVisibility(visibility);
        tilCapBase.setVisibility(visibility);
        tilChest.setVisibility(visibility);
    }

    private void toggleFemaleWidgets(int visibility) {
        //Top
        tilBust.setVisibility(visibility);
        tilHalfLength.setVisibility(visibility);
        tilKneeLength.setVisibility(visibility);
        tilHighWaist.setVisibility(visibility);
        //Trouser
        tilHips.setVisibility(visibility);
    }

    private void changeSubHead() {
        if (isMale()) {
            top.setText(R.string.top);
            trouser.setText(R.string.trouser);
        } else {
            top.setText(getResources().getString(R.string.top_or_gown_title));
            trouser.setText(getResources().getString(R.string.trouser_or_skirt_title));
        }
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
    public void setNotification(Client client) {
        NotificationService.setNotification(getActivity(), client, true);
        //TODO: cancel notification when a Job is delivered
    }

    @Override
    public void result(boolean isPending) {
        Intent data = new Intent();
        data.putExtra(ClientsFragment.EXTRA_ADD_CLIENT, isPending);

        getActivity().setResult(Activity.RESULT_OK, data);
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
        set(capBase, client.getCapBase());
        set(shoulder, client.getShoulder());
        set(longSleeve, client.getLongSleeve());
        set(cuff, client.getCuff());
        set(topLength, client.getTopOrGownLength());
        set(shortSleeve, client.getShortSleeve());
        set(roundSleeve, client.getRoundSleeve());
        set(halfLength, client.getHalfLength());
        set(kneeLength, client.getKneeLength());
        set(highWaist, client.getHighWaist());

        //Trouser
        set(waist, client.getWaist());
        set(thigh, client.getThigh());
        set(trouserLength, client.getTrouserLength());
        set(bottom, client.getBottom());
        set(hips, client.getHips());

        clientId = client.getId();

        if (sex.equals(Client.MALE)) {
            set(chest, client.getChestOrBust());
        } else {
            set(bust, client.getChestOrBust());
        }
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
        c.setSex(sex);
        c.setReceivedDate(new Date());
        if (formattedDate != null) {
            c.setDeliveryDate(Util.stringToDate(formattedDate));
        }

        if (sex.equals(Client.MALE)) {
            c.setChestOrBust(setValue(chest.getText().toString()));
        } else {
            c.setChestOrBust(setValue(bust.getText().toString()));
        }

        //Measurement - Top
        c.setCapBase(setValue(capBase.getText().toString()));
        c.setShoulder(setValue(shoulder.getText().toString()));
        c.setLongSleeve(setValue(longSleeve.getText().toString()));
        c.setCuff(setValue(cuff.getText().toString()));
        c.setTopOrGownLength(setValue(topLength.getText().toString()));
        c.setRoundSleeve(setValue(roundSleeve.getText().toString()));
        c.setShortSleeve(setValue(shortSleeve.getText().toString()));
        c.setHalfLength(setValue(halfLength.getText().toString()));
        c.setKneeLength(setValue(kneeLength.getText().toString()));
        c.setHighWaist(setValue(highWaist.getText().toString()));

        //Trouser
        c.setWaist(setValue(waist.getText().toString()));
        c.setThigh(setValue(thigh.getText().toString()));
        c.setTrouserLength(setValue(trouserLength.getText().toString()));
        c.setBottom(setValue(bottom.getText().toString()));
        c.setHips(setValue(hips.getText().toString()));

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

    private boolean isMale() {
        return sex.equals(Client.MALE);
    }
}
