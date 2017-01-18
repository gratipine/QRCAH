package com.endarpine.cahqr;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

public class exceptionPopUp extends DialogFragment {
    static String ALERT;
    static final String ACCEPT = "Ok";
    static final String REPORT = "Report";

    public exceptionPopUp () {
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
                        })
                //todo:handle the report function
                .setNegativeButton(REPORT, new DialogInterface.OnClickListener(){
                    public
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
