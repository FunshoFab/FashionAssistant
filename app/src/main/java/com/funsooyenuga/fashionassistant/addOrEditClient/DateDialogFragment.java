package com.funsooyenuga.fashionassistant.addOrEditClient;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.funsooyenuga.fashionassistant.R;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DateDialogFragment extends DialogFragment {

    public static final String EXTRA_DATE = "dateDialogFragment";

    private DatePicker datePicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.date_picker, null);
        datePicker = (DatePicker) v.findViewById(R.id.date_picker);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_dialog_delivery_date)
                .setPositiveButton(R.string.date_dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();

                        Date date = new GregorianCalendar(year, month, day).getTime();
                        sendResult(Activity.RESULT_OK, date);

                    }
                })
                .setNegativeButton(R.string.dialog_cancel, null)
                .create();

        return dialog;
    }

    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() != null) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_DATE, date);

            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
        }
    }
}
