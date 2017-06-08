package com.funsooyenuga.fashionassistant.clients;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.addOrEditClient.AddClientActivity;
import com.funsooyenuga.fashionassistant.data.Client;
import com.funsooyenuga.fashionassistant.data.DbManager.AndroidDatabaseManager;
import com.funsooyenuga.fashionassistant.data.loaders.ClientsLoader;
import com.funsooyenuga.fashionassistant.data.source.ClientsRepository;
import com.funsooyenuga.fashionassistant.notification.CancelNotifFragment;
import com.funsooyenuga.fashionassistant.util.Injection;
import com.funsooyenuga.fashionassistant.util.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.funsooyenuga.fashionassistant.clients.ClientsFilterType.MEASUREMENTS;
import static com.funsooyenuga.fashionassistant.clients.ClientsFilterType.PENDING_JOBS;

public class ClientsFragment extends CancelNotifFragment implements ClientsContract.View,
        DeliverDialog.Listener {

    public static final String TAG = "clientsFragment";
    public static final String EXTRA_ADD_CLIENT = "extraAddClient";

    public static final int RC_DELIVER_CLIENT = 2;
    private static final int RC_ADD_CLIENT = 1;

    private String CURRENT_FILTER = "currentFilter";

    private ClientAdapter adapter;

    private ClientsContract.Presenter presenter;
    private Listener listener;

    private TextView filterLabel, noDataText;
    private RecyclerView clientListRv;
    private LinearLayout noDataLayout;
    private ImageView noDataIcon;

    private ClientsFilterType filter = PENDING_JOBS;

    private boolean clientListInVisible = false;


    public static ClientsFragment newInstance() {
        return new ClientsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            try {
                listener = (Listener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must implement ClientsFragment.Listener");
            }
        }
    }

    public ClientsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            filter = (ClientsFilterType) savedInstanceState.getSerializable(CURRENT_FILTER);
        }

        adapter = new ClientAdapter(new ArrayList<Client>(0), itemListener);

        ClientsLoader loader = new ClientsLoader(getActivity().getApplicationContext());
        ClientsRepository repository = Injection.provideClientsRepository(getActivity().getApplicationContext());

        presenter = new ClientsPresenter(this, loader, getLoaderManager(), repository, filter);

        Log.d(TAG, "onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();

        Log.d(TAG, "onResume");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_clients, container, false);

        filterLabel = (TextView) v.findViewById(R.id.filterLabel);
        if (filter == MEASUREMENTS) {
            filterLabel.setText(getResources().getString(R.string.measurements));
        }

        noDataIcon = (ImageView) v.findViewById(R.id.noDataIcon);
        noDataText = (TextView) v.findViewById(R.id.noDataText);
        noDataLayout = (LinearLayout) v.findViewById(R.id.noDataLayout);
        clientListRv = (RecyclerView) v.findViewById(R.id.fragment_client_rv);
        clientListRv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        clientListRv.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(clientListRv.getContext(),
                layoutManager.getOrientation());
        clientListRv.addItemDecoration(divider);

        FloatingActionButton newClient = (FloatingActionButton) getActivity().findViewById(R.id.done);
        newClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addClient();
            }
        });

        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CURRENT_FILTER, filter);
    }

    @Override
    public void showClients(List<Client> clients, ClientsFilterType filter) {
        this.filter = filter;
        if (clientListInVisible) {
            filterLabel.setVisibility(View.VISIBLE);
            clientListRv.setVisibility(View.VISIBLE);

            noDataLayout.setVisibility(View.GONE);
        }
        adapter.refreshList(clients, filter);
    }

    @Override
    public void showMeasurement(String clientId, String sex, String name) {
        listener.onClientSelected(clientId, sex, name);
    }

    @Override
    public void showAddClientUi() {
        Intent intent = AddClientActivity.newIntent(getActivity());
        startActivityForResult(intent, RC_ADD_CLIENT);
    }

    @Override
    public void showNoDataUI(ClientsFilterType filter) {
        this.filter = filter;
        String message;
        int drawable;

        if (filter == PENDING_JOBS) {
            message = getResources().getString(R.string.no_pending_job);
            drawable = R.drawable.ic_done_all_black_24dp;
        } else {
            message = getResources().getString(R.string.no_measurement);
            drawable = R.drawable.ic_content_paste_black_24dp;
        }

        filterLabel.setVisibility(View.GONE);
        clientListRv.setVisibility(View.GONE);

        noDataLayout.setVisibility(View.VISIBLE);
        noDataText.setText(message);
        noDataIcon.setImageResource(drawable);

        clientListInVisible = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_ADD_CLIENT && resultCode == Activity.RESULT_OK) {
            boolean isPending = data.getExtras().getBoolean(EXTRA_ADD_CLIENT);

            if (isPending) {
                showMessage(getString(R.string.new_job_added));
                filterClients(PENDING_JOBS);
            } else {
                showMessage(getString(R.string.new_measurement));
                filterClients(MEASUREMENTS);
            }
        }
    }

    @Override
    public void onDeliverClick(String clientId) {
        presenter.setDelivered(clientId);
    }

    @Override
    public void showDeliveredMessage() {
        showMessage(getString(R.string.delivered));
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_client_list, menu);
        MenuItem item = menu.findItem(R.id.choose_filter);

        if (item != null) {
            Util.tintMenuIcon(getActivity(), item, R.color.white);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.choose_filter:
                showFilterPopUp();
                return true;
            case R.id.show_database:
                Intent intent = new Intent(getActivity(), AndroidDatabaseManager.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    private void showFilterPopUp() {
        PopupMenu popup = new PopupMenu(getContext(), getActivity().findViewById(R.id.choose_filter));
        popup.getMenuInflater().inflate(R.menu.menu_filter_clients, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.pending_jobs:
                        filterClients(PENDING_JOBS);
                        break;
                    case R.id.measurements:
                        filterClients(MEASUREMENTS);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        popup.show();
    }

    private void filterClients(ClientsFilterType filter) {
        presenter.filterClients(filter);
        if (filter == PENDING_JOBS) {
            filterLabel.setText(getResources().getString(R.string.pending_jobs));
        } else {
            filterLabel.setText(getResources().getString(R.string.measurements));
        }
    }

    /**
     * Listener for clicks on Client rows.
     */
    ClientItemListener itemListener = new ClientItemListener() {
        @Override
        public void onClientClick(String clientId, String sex, String name) {
            presenter.getMeasurement(clientId, sex, name);
        }

        @Override
        public void onMarkAsDeliveredClick(String clientId) {
            DeliverDialog dialog = DeliverDialog.newInstance(clientId);
            dialog.setTargetFragment(ClientsFragment.this, RC_DELIVER_CLIENT);
            dialog.show(getFragmentManager(), TAG);
        }
    };


//------------------------------------ ADAPTER --------------------------------------//

    public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.PendingJobVH> {

        private List<Client> clients;
        private ClientItemListener itemListener;
        private ClientsFilterType filter = PENDING_JOBS;

        private final int PENDING_JOB = 1;
        private final int MEASUREMENT = 2;

        public ClientAdapter(List<Client> clients, ClientItemListener itemListener) {
            this.clients = clients;
            this.itemListener = itemListener;
        }

        @Override
        public PendingJobVH onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View clientView = inflater.inflate(R.layout.pending_job_row, parent, false);

            return new PendingJobVH(clientView, itemListener);
        }

        @Override
        public void onBindViewHolder(final PendingJobVH holder, int position) {
            toggleCheckBoxAndDate(holder, filter);

            Client client = clients.get(position);
            holder.clientName.setText(client.getName());

            if (filter == PENDING_JOBS && client.getDeliveryDate() != null) {
                holder.dueDate.setText(setDate(client.getDeliveryDate()));
            } else {
                holder.dueDate.setText("");
            }
        }

        private String setDate(Date date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateToDisplay;

            Calendar tomorrowCal = Calendar.getInstance();
            Calendar yesterdayCal = Calendar.getInstance();

            tomorrowCal.add(Calendar.DAY_OF_YEAR, 1);
            yesterdayCal.add(Calendar.DAY_OF_YEAR, -1);

            Date tomorrow = tomorrowCal.getTime();
            Date yesterday = yesterdayCal.getTime();

            boolean isToday = DateUtils.isToday(date.getTime());
            boolean isYesterday = dateFormat.format(date).equals(dateFormat.format(yesterday));
            boolean isTomorrow = dateFormat.format(date).equals(dateFormat.format(tomorrow));

            if (isToday) {
                dateToDisplay = "Today";
            } else if (isYesterday) {
                dateToDisplay = "Yesterday";
            } else if (isTomorrow) {
                dateToDisplay = "Tomorrow";
            } else {
                dateToDisplay = Util.formatDateWithoutYear(date);
            }
            return dateToDisplay;
        }

        private void toggleCheckBoxAndDate(PendingJobVH holder, ClientsFilterType filter) {
            if (filter == MEASUREMENTS) {
                holder.dueDate.setVisibility(View.GONE);
                holder.deliverCb.setVisibility(View.GONE);
            } else {
                holder.dueDate.setVisibility(View.VISIBLE);
                holder.deliverCb.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return clients.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (clients.get(position).isPending()) {
                return PENDING_JOB;
            } else {
                return MEASUREMENT;
            }
        }

        public class PendingJobVH extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView clientName, dueDate;
            public CheckBox deliverCb;

            private ClientItemListener listener;

            public PendingJobVH(View itemView, ClientItemListener listener) {
                super(itemView);
                this.listener = listener;

                clientName = (TextView) itemView.findViewById(R.id.client_name);
                dueDate = (TextView) itemView.findViewById(R.id.due_date);
                deliverCb = (CheckBox) itemView.findViewById(R.id.cb_delivered);

                deliverCb.setOnClickListener(this);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Client client = clients.get(position);

                if (v.getId() == itemView.getId()) {
                    listener.onClientClick(client.getId(), client.getSex(), client.getName());
                } else if (v.getId() == deliverCb.getId()) {
                    deliverCb.setChecked(false);
                    listener.onMarkAsDeliveredClick(client.getId());
                }
            }
        }

        public void refreshList(List<Client> clients, ClientsFilterType filter) {
            this.clients = clients;
            this.filter = filter;

            notifyDataSetChanged();
        }
    }

    interface ClientItemListener {

        void onClientClick(String clientId, String sex, String name);

        void onMarkAsDeliveredClick(String clientId);
    }

    interface Listener {

        void onClientSelected(String clientId, String sex, String name);
    }

}
