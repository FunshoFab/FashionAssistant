package com.funsooyenuga.fashionassistant.TestArena;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.funsooyenuga.fashionassistant.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateActivity extends AppCompatActivity implements DateDialogFragment.Listener {

    private TextView displayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_test);

        displayDate = (TextView) findViewById(R.id.display_date_textview);
        Button showDateDialog = (Button) findViewById(R.id.show_date_button);
        showDateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                DateDialogFragment ddf = new DateDialogFragment();
                ddf.show(fm, "Date");
            }
        });
    }

    @Override
    public void onDateSelect(Date date) {
        String pattern = "EEE, d MMM yyyy";
        SimpleDateFormat dateFmt = new SimpleDateFormat(pattern);
        String formattedDate = dateFmt.format(date);
        displayDate.setText(formattedDate);
    }
}
