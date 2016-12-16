package com.funsooyenuga.fashionassistant.clients;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.data.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientsFragment extends Fragment implements ClientsContract.View {

    private ClientAdapter adapter;
    private ClientsContract.ActionListener actionListener;

    public static ClientsFragment newInstance() {
        return new ClientsFragment();
    }

    public interface Listener {

        void onClientSelected(String clientId);
    }

    private Listener listener;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement ClientsFragment.Listener");
        }
    }

    public ClientsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ClientAdapter(new ArrayList<Client>(0), itemListener);
        actionListener = new ClientsPresenter(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        actionListener.loadClients();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_client, container, false);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.fragment_client_rv);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    ClientItemListener itemListener = new ClientItemListener() {
        @Override
        public void onClientClick(String clientId) {
            actionListener.getMeasurement(clientId);
        }
    };

    @Override
    public void showClients(List<Client> clients) {
        adapter.refreshList(clients);
    }

    @Override
    public void showMeasurement(String clientId) {
        listener.onClientSelected(clientId);
    }

    @Override
    public void showAddClientUi() {

    }

    public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {

        private List<Client> clients;
        private ClientItemListener itemListener;

        public ClientAdapter(List<Client> clients, ClientItemListener itemListener) {
            this.clients = clients;
            this.itemListener = itemListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View clientView = inflater.inflate(R.layout.client_list, parent, false);

            return new ViewHolder(clientView, itemListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Client client = clients.get(position);
            holder.clientName.setText(client.getName());
            holder.dueDate.setText(client.getDueDate());
        }

        @Override
        public int getItemCount() {
            return clients.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView clientName, dueDate;
            private ClientItemListener listener;

            public ViewHolder(View itemView, ClientItemListener listener) {
                super(itemView);
                this.listener = listener;
                clientName = (TextView) itemView.findViewById(R.id.client_name);
                dueDate = (TextView) itemView.findViewById(R.id.due_date);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Client client = clients.get(position);
                listener.onClientClick(client.getId().toString());
            }
        }

        public void refreshList(List<Client> clients) {
            this.clients = clients;
            notifyDataSetChanged();
        }

    }

    public interface ClientItemListener {

        void onClientClick(String clientId);
    }
}
