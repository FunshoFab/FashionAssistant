package com.funsooyenuga.fashionassistant.addOrEditClient;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
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

import static com.funsooyenuga.fashionassistant.util.Util.set;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddOrEditFemaleClientFragment extends Fragment
        implements AddOrEditClientContract.View {

    public static final String TAG = "FemaleFragment";

    public static final int RC_DATE_DIALOG = 2;

    private static final String ARG_CLIENT_ID = "arg_client_id";

    private String clientId;

    private String formattedDate;

    private AddOrEditClientContract.Presenter presenter;

    private FloatingActionButton fab;

    //TextView
    private TextView top, trouser;
    //Client info
    private EditText name, phoneNumber, addInfo;
    private Button deliveryDate;
    //Top
    private EditText shoulder, bust, longSleeve, roundSleeve, shortSleeve, cuff, topLength, halfLength, kneeLength, highWaist;
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
        initFab(getUserVisibleHint());

        Log.d(TAG, "onCreateView()");
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
        deliveryDate = (Button) v.findViewById(R.id.delivery_date_button);
        deliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        //Top
        shoulder = (EditText) v.findViewById(R.id.et_shoulder);
        bust = (EditText) v.findViewById(R.id.et_bust);
        longSleeve = (EditText) v.findViewById(R.id.et_long_sleeve);
        shortSleeve = (EditText) v.findViewById(R.id.et_short_sleeve);
        roundSleeve = (EditText) v.findViewById(R.id.et_round_sleeve);
        cuff = (EditText) v.findViewById(R.id.et_cuff);
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
    public void result(boolean isPending) {
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
        set(shoulder, client.getShoulder());
        set(bust, client.getChestOrBust());
        set(longSleeve, client.getLongSleeve());
        set(cuff, client.getCuff());
        set(topLength, client.getTopOrGownLength());
        set(halfLength, client.getHalfLength());
        set(kneeLength, client.getKneeLength());
        set(highWaist, client.getHighWaist());
        set(shortSleeve, client.getShortSleeve());
        set(roundSleeve, client.getRoundSleeve());

        //Trouser
        set(waist, client.getWaist());
        set(thigh, client.getThigh());
        set(trouserLength, client.getTrouserLength());
        set(bottom, client.getBottom());
        set(hips, client.getHips());
    }

    private Client fetchClientDetail() {
        Client c = new Client();

        //Client Info
        c.setName(setName(name.getText().toString().trim()));
        c.setPhoneNumber(phoneNumber.getText().toString());
        c.setAddInfo(addInfo.getText().toString());
        c.setSex("f");
        c.setReceivedDate(new Date());

        if (formattedDate != null) {
            c.setDeliveryDate(Util.stringToDate(formattedDate));
        }

        //Measurement - Top/Gown
        c.setShoulder(setValue(shoulder.getText().toString()));
        c.setChestOrBust(setValue(bust.getText().toString()));
        c.setLongSleeve(setValue(longSleeve.getText().toString()));
        c.setCuff(setValue(cuff.getText().toString()));
        c.setTopOrGownLength(setValue(topLength.getText().toString()));
        c.setHalfLength(setValue(halfLength.getText().toString()));
        c.setKneeLength(setValue(kneeLength.getText().toString()));
        c.setHighWaist(setValue(highWaist.getText().toString()));
        c.setRoundSleeve(setValue(roundSleeve.getText().toString()));
        c.setShortSleeve(setValue(shortSleeve.getText().toString()));

        //Trouser/skirt
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
}
