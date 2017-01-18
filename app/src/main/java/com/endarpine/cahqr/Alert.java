package com.endarpine.cahqr;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class Alert extends DialogFragment {
    static String ALERT;
    static final String ACCEPT = "Ok";

    public Alert () {
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(ALERT)
                .setPositiveButton(ACCEPT, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //going back to the previous screen
                        closeFragment();
                    }
                });
        Bundle bundle = getArguments();
        ALERT = bundle.getString("text");
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void closeFragment() {
        getActivity().getFragmentManager().popBackStack();
    }
}
