package com.funsooyenuga.fashionassistant.clientdetail;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.funsooyenuga.fashionassistant.R;

/**
 * Created by FAB THE GREAT on 16/01/2017.
 */

public class DeleteDialog extends DialogFragment {

    private Listener listener;

    public interface Listener {

        void onDeleteClick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (Listener) getTargetFragment();
        } catch (ClassCastException ex) {
            throw new ClassCastException("Hosting fragment must implement DeleteDialog.Listener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Delete")
                .setMessage("This client will be deleted")
                .setPositiveButton(getResources().getString(R.string.date_dialog_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDeleteClick();
                    }
                })
                .setNegativeButton("CANCEL", null)
                .create();

        return dialog;
    }
}
