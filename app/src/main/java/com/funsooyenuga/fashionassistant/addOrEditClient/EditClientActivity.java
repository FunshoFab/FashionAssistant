package com.funsooyenuga.fashionassistant.addOrEditClient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.funsooyenuga.fashionassistant.R;
import com.funsooyenuga.fashionassistant.util.Util;

public class EditClientActivity extends AppCompatActivity {

    private static final String EXTRA_CLIENT_ID = "clientId";

    private static final String EXTRA_SEX = "sex";

    private String clientId, sex;

    public static Intent newIntent(Context context, @NonNull String clientId, @NonNull String sex) {
        Intent intent = new Intent(context, EditClientActivity.class);
        intent.putExtra(EXTRA_CLIENT_ID, clientId);
        intent.putExtra(EXTRA_SEX, sex);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        clientId = getIntent().getStringExtra(EXTRA_CLIENT_ID);
        sex = getIntent().getStringExtra(EXTRA_SEX);

        Fragment fragment = AddOrEditClientFragment.newInstance(clientId, sex);
        String tag = AddOrEditClientFragment.TAG;

        Util.hostFragment(getSupportFragmentManager(), R.id.content_frame, fragment, tag);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
