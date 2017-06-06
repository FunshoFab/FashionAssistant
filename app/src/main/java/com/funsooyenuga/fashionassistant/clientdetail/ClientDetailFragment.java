package com.funsooyenuga.fashionassistant.clientdetail;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.addOrEditClient.EditClientActivity;
import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.loaders.ClientLoader;
import com.funsooyenuga.fashionassistant.data.source.ClientDataSource;
import com.funsooyenuga.fashionassistant.util.Injection;
import com.funsooyenuga.fashionassistant.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    private RecyclerView topListView, trouserListView;
    private CardView topListCard, trouserListCard, clientInfoCard;
    private LinearLayout phoneNumberLayout, addInfoLayout, noDataLayout;
    private TextView phoneNumberDetail, addInfoDetail, noDataText;
    private ImageView noDataIcon;

    private Listener listener;
    private ClientDetailContract.Presenter presenter;

    private String clientId;
    private String sex;

    private ClientDetailAdapter topListAdapter, trouserListAdapter;
    private List<ClientDetail> topList, trouserList;


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

        topListAdapter = new ClientDetailAdapter(new ArrayList<ClientDetail>(0));
        trouserListAdapter = new ClientDetailAdapter(new ArrayList<ClientDetail>(0));

        topList = new ArrayList<>();
        trouserList = new ArrayList<>();

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

        topListCard = (CardView) v.findViewById(R.id.top_detail_card);
        trouserListCard = (CardView) v.findViewById(R.id.trouser_detail_card);
        clientInfoCard = (CardView) v.findViewById(R.id.client_info_detail_card);
        phoneNumberLayout = (LinearLayout) v.findViewById(R.id.phone_number_detail);
        addInfoLayout = (LinearLayout) v.findViewById(R.id.add_info_detail);
        phoneNumberDetail = (TextView) v.findViewById(R.id.tv_phone_number);
        addInfoDetail = (TextView) v.findViewById(R.id.tv_add_info_detail);
        noDataLayout = (LinearLayout) v.findViewById(R.id.noDataLayout);
        noDataIcon = (ImageView) v.findViewById(R.id.noDataIcon);
        noDataText = (TextView) v.findViewById(R.id.noDataText);

        topListView = (RecyclerView) v.findViewById(R.id.top_list);
        topListView.setFocusable(false);
        trouserListView = (RecyclerView) v.findViewById(R.id.trouser_list);
        trouserListView.setFocusable(false);

        topListView.setAdapter(topListAdapter);
        trouserListView.setAdapter(trouserListAdapter);

        LinearLayoutManager topListManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager trouserListManager = new LinearLayoutManager(getActivity());

        topListView.setLayoutManager(topListManager);
        trouserListView.setLayoutManager(trouserListManager);

        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), topListManager.getOrientation());
        topListView.addItemDecoration(decoration);
        trouserListView.addItemDecoration(decoration);

        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void showDetails(Client client) {
        setClientInfo(client);
        setMeasurement(client);
        displayDetails();
    }

    private void setClientInfo(Client client) {
        final String phoneNumber = client.getPhoneNumber();
        String addInfo = client.getAddInfo();

        if (phoneNumber.isEmpty()) {
            phoneNumberLayout.setVisibility(View.GONE);
        } else {
            phoneNumberLayout.setVisibility(View.VISIBLE);
            phoneNumberDetail.setText(phoneNumber);
            phoneNumberLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callClient(phoneNumber);
                }
            });
        }

        if (addInfo.isEmpty()) {
            addInfoLayout.setVisibility(View.GONE);
        } else {
            addInfoLayout.setVisibility(View.VISIBLE);
            addInfoDetail.setText(addInfo);
        }

        if (phoneNumber.isEmpty() && addInfo.isEmpty()) {
            clientInfoCard.setVisibility(View.GONE);
        } else {
            clientInfoCard.setVisibility(View.VISIBLE);
        }
    }

    private void setMeasurement(Client client) {
        if (!topList.isEmpty()) {
            topList.clear();
        }
        if (!trouserList.isEmpty()) {
            trouserList.clear();
        }
        setTopDetail(getResources().getString(R.string.tv_cap_base), client.getCapBase());
        setTopDetail(getResources().getString(R.string.tv_shoulder), client.getShoulder());
        setTopDetail(getResources().getString(R.string.tv_long_sleeve), client.getLongSleeve());
        setTopDetail(getResources().getString(R.string.tv_short_sleeve), client.getShortSleeve());
        setTopDetail(getResources().getString(R.string.tv_round_sleeve), client.getRoundSleeve());
        setTopDetail(getResources().getString(R.string.tv_cuff), client.getCuff());
        setTopDetail(getResources().getString(R.string.tv_length), client.getTopOrGownLength());
        setTopDetail(getResources().getString(R.string.tv_half_trouser_length), client.getHalfLength());
        setTopDetail(getResources().getString(R.string.tv_knee_trouser_length), client.getKneeLength());
        setTopDetail(getResources().getString(R.string.tv_high_waist), client.getHighWaist());

        setTrouserDetail(getResources().getString(R.string.tv_length), client.getTrouserLength());
        setTrouserDetail(getResources().getString(R.string.tv_waist), client.getWaist());
        setTrouserDetail(getResources().getString(R.string.tv_thigh), client.getThigh());
        setTrouserDetail(getResources().getString(R.string.tv_bottom), client.getBottom());
        setTrouserDetail(getResources().getString(R.string.tv_hips), client.getHips());

        if (client.getSex().equals(Client.MALE)) {
            setTopDetail(getResources().getString(R.string.tv_chest), client.getChestOrBust());
        } else {
            setTopDetail(getResources().getString(R.string.tv_bust), client.getChestOrBust());
        }
    }

    private void displayDetails() {
        if (topList.isEmpty()) {
            topListCard.setVisibility(View.GONE);
        } else {
            topListCard.setVisibility(View.VISIBLE);
            sortClientDetail(topList);
            topListAdapter.refreshData(topList);
        }

        if (trouserList.isEmpty()) {
            trouserListCard.setVisibility(View.GONE);
        } else {
            trouserListCard.setVisibility(View.VISIBLE);
            sortClientDetail(trouserList);
            trouserListAdapter.refreshData(trouserList);
        }

        if (topList.isEmpty() && trouserList.isEmpty()) {
            noDataLayout.setVisibility(View.VISIBLE);
            noDataText.setText(R.string.no_measurement);
            noDataIcon.setImageResource(R.drawable.ic_content_paste_black_24dp);
        } else {
            noDataLayout.setVisibility(View.GONE);
        }
    }

    private void callClient(String phoneNumber) {
        Uri phoneNumberUri = Uri.parse("tel:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_DIAL, phoneNumberUri);
        startActivity(intent);
    }

    private void sortClientDetail(List<ClientDetail> clientDetails) {
        Collections.sort(clientDetails, new Comparator<ClientDetail>() {
            @Override
            public int compare(ClientDetail o1, ClientDetail o2) {
                return o1.getAttribute().compareToIgnoreCase(o2.getAttribute());
            }
        });
    }

    private void setTopDetail(String attribute, double value) {
        if (value > 0) {
            topList.add(new ClientDetail(attribute, value));
        }
    }

    private void setTrouserDetail(String attribute, double value) {
        if (value > 0) {
            trouserList.add(new ClientDetail(attribute, value));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_EDIT_CLIENT && resultCode == Activity.RESULT_OK) {
            Snackbar.make(getView(), R.string.edit_saved, Snackbar.LENGTH_SHORT).show();
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

    /**
     * A class for the measurement details of a client
     */
    private class ClientDetail {
        String attribute;
        double value;

        public ClientDetail(String attribute, double value) {
            this.attribute = attribute;
            this.value = value;
        }

        public String getAttribute() {
            return attribute;
        }

        public double getValue() {
            return value;
        }
    }

    //------------------------------------ADAPTER-----------------------------------------------//

    public static class ClientDetailAdapter extends RecyclerView.Adapter<ClientDetailAdapter.ViewHolder> {

        private List<ClientDetail> clients;

        public ClientDetailAdapter(List<ClientDetail> clients) {
            this.clients = clients;
        }

        public void refreshData(List<ClientDetail> clients) {
            this.clients = clients;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return clients.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new ViewHolder(inflater.inflate(R.layout.client_detail_row, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ClientDetail client = clients.get(position);
            holder.attribute.setText(client.getAttribute());
            holder.value.setText(String.valueOf(client.getValue()));
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView attribute, value;

            public ViewHolder(View itemView) {
                super(itemView);

                attribute = (TextView) itemView.findViewById(R.id.client_detail_attribute);
                value = (TextView) itemView.findViewById(R.id.client_detail_value);
            }
        }
    }

    public interface Listener {

        void onClientDeleted();
    }

}
