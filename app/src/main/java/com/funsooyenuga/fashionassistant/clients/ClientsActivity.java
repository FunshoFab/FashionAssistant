package com.funsooyenuga.fashionassistant.clients;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.clientdetail.ClientDetailActivity;
import com.funsooyenuga.fashionassistant.clientdetail.ClientDetailFragment;

public class ClientsActivity extends AppCompatActivity implements ClientsFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initFragment(ClientsFragment.newInstance(), R.id.content_frame);
    }

    private void initFragment(Fragment fragment, int containerId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(containerId, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClientSelected(String clientId) {
        Boolean isTwoPane = findViewById(R.id.detail_container) != null;

        if (isTwoPane) {
            initFragment(ClientDetailFragment.newInstance(clientId), R.id.detail_container);
            /*FragmentTransaction ftt = getSupportFragmentManager().beginTransaction();
            ftt.add(R.id.detail_container, ClientDetailFragment.newInstance(clientId))
                    .addToBackStack(null)
                    .commit();*/
        } else {
            Intent intent = ClientDetailActivity.newIntent(this, clientId);
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

        return super.onOptionsItemSelected(item);
    }
}
