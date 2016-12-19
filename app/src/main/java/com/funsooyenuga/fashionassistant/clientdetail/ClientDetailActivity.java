package com.funsooyenuga.fashionassistant.clientdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.util.HelperMethods;

public class ClientDetailActivity extends AppCompatActivity {

    public static final String EXTRA_CLIENT_ID = "client_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

        String clientId = getIntent().getStringExtra(EXTRA_CLIENT_ID);

        HelperMethods.hostFragment(getSupportFragmentManager(), R.id.content_frame,
                ClientDetailFragment.newInstance(clientId));
    }

    public static Intent newIntent(Context context, String clientId) {
        Intent intent = new Intent(context, ClientDetailActivity.class);
        intent.putExtra(EXTRA_CLIENT_ID, clientId);
        return intent;
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
