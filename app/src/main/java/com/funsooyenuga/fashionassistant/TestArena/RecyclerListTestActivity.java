package com.funsooyenuga.fashionassistant.TestArena;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.funsooyenuga.fashionassistant.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerListTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_list_test);

        RecyclerView firstRv = (RecyclerView) findViewById(R.id.firstRv);
        RecyclerView secondRv = (RecyclerView) findViewById(R.id.secondRv);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        MyAdapter adapter = new MyAdapter(new ArrayList<ClientObj>(0));

        firstRv.setAdapter(adapter);
        secondRv.setAdapter(adapter);

        firstRv.setLayoutManager(manager);
        secondRv.setLayoutManager(new LinearLayoutManager(this));

        adapter.refresh(provideClients());
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<ClientObj> dummyClients;

        public MyAdapter(List<ClientObj> stubClients) {
            dummyClients = stubClients;
        }

        public void refresh(List<ClientObj> stubClients) {
            dummyClients = stubClients;
        }

        @Override
        public int getItemCount() {
            return dummyClients.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View viewHolder = inflater.inflate(R.layout.client_list_row, parent, false);
            return new ViewHolder(viewHolder);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ClientObj client = dummyClients.get(position);
            holder.clientName.setText(client.getName());
            holder.dueDate.setText(client.getDeliveryDate());
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView clientName, dueDate;
            public CheckBox deliverCb;

            public ViewHolder(View itemView) {
                super(itemView);

                clientName = (TextView) itemView.findViewById(R.id.client_name);
                dueDate = (TextView) itemView.findViewById(R.id.due_date);
                deliverCb = (CheckBox) itemView.findViewById(R.id.cb_delivered);
            }
        }
    }

    public class ClientObj {
        private String name, deliveryDate;

        public ClientObj(String name, String deliveryDate) {
            this.name = name;
            this.deliveryDate = deliveryDate;
        }

        public String getDeliveryDate() {
            return deliveryDate;
        }

        public void setDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public List<ClientObj> provideClients() {
        List<ClientObj> clients = new ArrayList<>();
        for (int i=0; i<20; i++) {
            clients.add(new ClientObj("Funsho o", "I dont know"));
        }
        return clients;
    }
}
