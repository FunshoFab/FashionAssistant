package com.funsooyenuga.fashionassistant.clientdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.util.Util;

public class ClientDetailActivity extends AppCompatActivity implements ClientDetailFragment.Listener  {

    public static final String EXTRA_CLIENT_ID = "client_id";
    public static final String EXTRA_SEX = "sex";
    public static final String EXTRA_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

        String clientId = getIntent().getStringExtra(EXTRA_CLIENT_ID);
        String sex = getIntent().getStringExtra(EXTRA_SEX);
        String name = getIntent().getStringExtra(EXTRA_NAME);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        actionBar.setTitle(name);

        Util.hostFragment(getSupportFragmentManager(), R.id.content_frame,
                ClientDetailFragment.newInstance(clientId, sex), ClientDetailFragment.TAG);
    }

    public static Intent newIntent(Context context, String clientId, String sex, String name) {
        Intent intent = new Intent(context, ClientDetailActivity.class);
        intent.putExtra(EXTRA_CLIENT_ID, clientId);
        intent.putExtra(EXTRA_SEX, sex);
        intent.putExtra(EXTRA_NAME, name);

        return intent;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClientDeleted() {
        finish();
    }
}
