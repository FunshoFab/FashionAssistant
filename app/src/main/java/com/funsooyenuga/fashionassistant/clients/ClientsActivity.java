package com.funsooyenuga.fashionassistant.clients;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.clientdetail.ClientDetailActivity;
import com.funsooyenuga.fashionassistant.clientdetail.ClientDetailFragment;
import com.funsooyenuga.fashionassistant.data.DbManager.AndroidDatabaseManager;
import com.funsooyenuga.fashionassistant.util.Util;

public class ClientsActivity extends AppCompatActivity implements ClientsFragment.Listener {

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();

        Util.hostFragment(fm, R.id.content_frame, ClientsFragment.newInstance());
    }

    @Override
    public void onClientSelected(String clientId, String sex) {
        Boolean isTwoPane = findViewById(R.id.detail_container) != null;

        if (isTwoPane) {
            Util.hostFragment(fm, R.id.detail_container, ClientDetailFragment.newInstance(clientId, sex));
        } else {
            Intent intent = ClientDetailActivity.newIntent(this, clientId, sex);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.show_database) {
            Intent intent = new Intent(this, AndroidDatabaseManager.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
