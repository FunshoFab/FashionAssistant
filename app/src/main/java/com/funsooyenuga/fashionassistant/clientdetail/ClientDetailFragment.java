package com.funsooyenuga.fashionassistant.clientdetail;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.addOrEditClient.EditClientActivity;
import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.loaders.ClientLoader;
import com.funsooyenuga.fashionassistant.data.source.ClientDataSource;
import com.funsooyenuga.fashionassistant.util.Injection;
import com.funsooyenuga.fashionassistant.util.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientDetailFragment extends Fragment implements ClientDetailContract.View,
                DeleteDialog.Listener {

    public static final String TAG = "clientDetailFragment";
    private static final String DELETE_DIALOG = "delete_dialog";

    private static final String ARG_CLIENT_ID = "clientId";
    private static final String ARG_SEX = "sex";

    private static final int RC_EDIT_CLIENT = 1;
    private static final int RC_DELETE_DIALOG = 2;


    private Listener listener;

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
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            try {
                listener = (Listener) context;
            } catch (ClassCastException e) {
                Log.e(TAG, getActivity().toString() + " must implement ClientDetailFragment.Listener");
            }
        }
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

        setHasOptionsMenu(true);

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_client_detail, menu);

        MenuItem deleteClientItem = menu.findItem(R.id.delete_client);
        if (deleteClientItem != null) {
            Util.tintMenuIcon(getActivity(), deleteClientItem, R.color.white);
        }

        MenuItem editClientItem = menu.findItem(R.id.edit_client);
        if (editClientItem != null) {
            Util.tintMenuIcon(getActivity(), editClientItem, R.color.white);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.delete_client:
                showDeleteDialog();
                return true;
            case R.id.edit_client:
                startEditClientActivity();
                return true;
            default:
                return false;
        }
    }

    private void showDeleteDialog() {
        DeleteDialog dialog = new DeleteDialog();
        dialog.setTargetFragment(this, RC_DELETE_DIALOG);
        dialog.show(getFragmentManager(), DELETE_DIALOG);
    }

    private void startEditClientActivity() {
        Intent intent = EditClientActivity.newIntent(getActivity().getApplicationContext(),
                clientId, sex);
        startActivityForResult(intent, RC_EDIT_CLIENT);
    }

    @Override
    public void onDeleteClick() {
        presenter.deleteClient();
    }

    @Override
    public void returnToClientsList() {
        listener.onClientDeleted();
    }

    public interface Listener {

        void onClientDeleted();
    }
}
