package com.funsooyenuga.fashionassistant.clientdetail;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.addOrEditClient.EditClientActivity;
import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.loaders.ClientLoader;
import com.funsooyenuga.fashionassistant.data.source.ClientDataSource;
import com.funsooyenuga.fashionassistant.util.Injection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientDetailFragment extends Fragment implements ClientDetailContract.View {

    private static final String ARG_CLIENT_ID = "clientId";

    private static final int RC_EDIT_CLIENT = 1;

    private static final String ARG_SEX = "sex";

    private String clientId;

    private String sex;

    private ClientDetailContract.Presenter presenter;

    private TextView tv_name, tv_sex;

    public static ClientDetailFragment newInstance(String clientId, String sex) {
        Bundle args = new Bundle();
        args.putString(ARG_CLIENT_ID, clientId);
        args.putString(ARG_SEX, sex);

        ClientDetailFragment fragment = new ClientDetailFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public ClientDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clientId = getArguments().getString(ARG_CLIENT_ID);
        sex = getArguments().getString(ARG_SEX);

        ClientDataSource repository = Injection.provideClientsRepository(getActivity().getApplicationContext());
        ClientLoader loader = new ClientLoader(getActivity().getApplicationContext(), clientId);

        presenter = new ClientDetailPresenter(clientId, this, loader, getLoaderManager(), repository);
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_client_detail, container, false);

        tv_name = (TextView) v.findViewById(R.id.client_name);
        tv_sex = (TextView) v.findViewById(R.id.client_sex);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.edit_client);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EditClientActivity.newIntent(getActivity().getApplicationContext(),
                        clientId, sex);
                startActivityForResult(intent, RC_EDIT_CLIENT);
            }
        });
        return v;
    }

    @Override
    public void showDetails(Client client) {
        tv_name.setText(client.getName());
        tv_sex.setText(client.getSex());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_EDIT_CLIENT && resultCode == Activity.RESULT_OK) {
            Snackbar.make(tv_name, R.string.edit_saved, Snackbar.LENGTH_SHORT).show();
        }
    }
}
