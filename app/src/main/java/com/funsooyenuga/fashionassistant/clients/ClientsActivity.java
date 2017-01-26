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

public class ClientsActivity extends AppCompatActivity implements ClientsFragment.Listener,
        ClientDetailFragment.Listener {

    private FragmentManager fm;

    private ClientDetailFragment clientDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();

        ClientsFragment fragment = findClientsFragment();

        if (fragment == null) {
            Util.hostFragment(fm, R.id.content_frame, ClientsFragment.newInstance(), ClientsFragment.TAG);
        }
    }

    @Override
    public void onClientSelected(String clientId, String sex, String name) {
        Boolean isTwoPane = findViewById(R.id.detail_container) != null;

        if (isTwoPane) {
            clientDetailFragment = ClientDetailFragment.newInstance(clientId, sex);
            Util.hostFragment(fm, R.id.detail_container, clientDetailFragment, ClientDetailFragment.TAG);
        } else {
            Intent intent = ClientDetailActivity.newIntent(this, clientId, sex, name);
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

    private ClientsFragment findClientsFragment() {
        return (ClientsFragment) getSupportFragmentManager().findFragmentByTag(ClientsFragment.TAG);
    }

    @Override
    public void onClientDeleted() {
        getSupportFragmentManager().beginTransaction().remove(clientDetailFragment).commit();
    }
}
