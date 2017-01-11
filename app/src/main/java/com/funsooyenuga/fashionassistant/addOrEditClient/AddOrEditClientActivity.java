package com.funsooyenuga.fashionassistant.addOrEditClient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.funsooyenuga.fashionassistant.R;

public class AddOrEditClientActivity extends AppCompatActivity {

    private static final String TAG = "AddOrEditClientActivity";

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private static final String EXTRA_CLIENT_ID = "extra_client_id";

    private static final String EXTRA_SEX = "extra_sex";

    private String clientId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));

        clientId = getIntent().getStringExtra(EXTRA_CLIENT_ID);

        if (getIntent().hasExtra(EXTRA_SEX)) {
            String sex = getIntent().getStringExtra(EXTRA_SEX);
            if (sex == "m")
                mSectionsPagerAdapter.getItem(0);
            else if (sex == "f")
                mSectionsPagerAdapter.getItem(1);
            else
                Log.e(TAG, "EXTRA_SEX has an invalid value of: " + sex);
        }

    }

    public static Intent newIntent(Context context, @Nullable String clientId, @Nullable String sex) {
        Intent intent = new Intent(context, AddOrEditClientActivity.class);
        intent.putExtra(EXTRA_CLIENT_ID, clientId);
        intent.putExtra(EXTRA_SEX, sex);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_client_activity2, menu);
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


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return AddOrEditMaleClientFragment.newInstance(clientId);
                case 1:
                    return AddOrEditFemaleClientFragment.newInstance(clientId);
                default:
                    return new AddOrEditMaleClientFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "MALE";
                case 1:
                    return "FEMALE";
            }
            return null;
        }
    }
}
