package com.endarpine.cahqr;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class addCode extends FragmentActivity {

    final static String EXTRA_MESSAGE = "Passed from the adding code activity";
    final static String ALERT_TEXT = "You haven't written anything, so no code for you!";

    Button addCodeSubmitButton;
    EditText addCodeEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_code);

        addCodeSubmitButton = (Button)findViewById(R.id.addCodeSubmitButton);
        addCodeEditText = (EditText)findViewById(R.id.addCodeEditText);

    }


    public void codeAdded(View view){
        String textToAdd = addCodeEditText.getText().toString();
        if(textToAdd==null){
            //shows an alert if the user box is empty and prompts the user to go back
            showAlert();
        } else {
            //passes the user text to the activity which displays the QR code
            Intent toReadyCode = new Intent(this, codePicture.class);
            toReadyCode.putExtra(EXTRA_MESSAGE, textToAdd);
            startActivity(toReadyCode);
        }
    }
    //creates an alert object and shows its message if the user did not write a code text before pressing Submit button
    private void showAlert(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(addCode.this);
        builder1.setMessage("Write your message here.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();


    }
}
