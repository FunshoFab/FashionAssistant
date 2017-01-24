package com.funsooyenuga.fashionassistant.clients;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import com.funsooyenuga.fashionassistant.R;

/**
 * Created by FAB THE GREAT on 18/01/2017.
 */

public class DeliverDialog extends DialogFragment {

    private static final String ARG_CLIENT_ID = "clientId";

    private String clientId;

    private Listener listener;

    public interface Listener {

        void onDeliverClick(String clientId);
    }

    public static DeliverDialog newInstance(@NonNull String clientId) {
        DeliverDialog dialog = new DeliverDialog();

        Bundle args = new Bundle();
        args.putString(ARG_CLIENT_ID, clientId);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Fragment fragment = getTargetFragment();

        try {
            listener = (DeliverDialog.Listener) fragment;
        } catch (ClassCastException ex) {
            throw new ClassCastException("Hosting fragment must implement DeliverDialog.Listener");
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clientId = getArguments().getString(ARG_CLIENT_ID);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_mark_delivered_title)
                .setMessage(R.string.dialog_deliver_client)
                .setPositiveButton(R.string.dialog_mark_delivered_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDeliverClick(clientId);
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, null)
                .create();

        return dialog;
    }
}
