package com.funsooyenuga.fashionassistant;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

/**
 * Created by FAB THE GREAT on 30/11/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ListRow> {

    List<Client> clients;

    public RecyclerAdapter(List<Client> clients) {
        this.clients = clients;
    }

    public class ListRow extends RecyclerView.ViewHolder {
        public ListRow(View view) {
            super(view);
        }
    }

    @Override
    public ListRow onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ListRow(v);
    }

    @Override
    public void onBindViewHolder(ListRow listRow, int position) {

    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

}
