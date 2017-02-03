package com.funsooyenuga.fashionassistant.TestArena;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListTestActivity extends AppCompatActivity {

    private List<ClientDetail> clientDetails = new ArrayList<>();

    private String[] data = {"bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla",
            "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla",
            "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla", "bla"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);

        ListView firstList = (ListView) findViewById(R.id.firstList);
        ListView secondList = (ListView) findViewById(R.id.secondList);

/*
        for (int i=0; i<20; i++) {
            data[i] = "bla " + i;
        }
*/

        firstList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data));
        secondList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data));

        /*ClientDetailAdapter adapter = new ClientDetailAdapter(new ArrayList<ClientDetail>(0));
        firstList.setAdapter(adapter);
        secondList.setAdapter(adapter);

        for (int i=0; i<20; i++) {
            clientDetails.add(new ClientDetail("bullshit", 2));
        }

        adapter.refreshData(clientDetails);
*/
        Util.setDynamicListHeight(firstList);
        Util.setDynamicListHeight(secondList);
    }

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


    private static class ClientDetailAdapter extends BaseAdapter {

        private List<ClientDetail> clientDetail;

        public ClientDetailAdapter(List<ClientDetail> clientDetail) {
            this.clientDetail = clientDetail;
        }

        public void refreshData(List<ClientDetail> newDetail) {
            this.clientDetail = newDetail;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return clientDetail.size();
        }

        @Override
        public ClientDetail getItem(int position) {
            return clientDetail.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                view = inflater.inflate(R.layout.client_detail_row, parent, false);
            }

            ClientDetail detail = getItem(position);

            TextView attribute = (TextView) view.findViewById(R.id.client_detail_attribute);
            TextView value = (TextView) view.findViewById(R.id.client_detail_value);

            attribute.setText(detail.getAttribute());
            value.setText(String.valueOf(detail.getValue()));

            return view;
        }
    }

}
