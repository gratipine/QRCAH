package com.endarpine.cahqr;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
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
    //todo: figure out how to pass the message towards the next activity and create a picture
    //todo:add code which check if the user has written something or not
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
        FragmentManager fm = getSupportFragmentManager();
        Alert alert = new Alert();
        Bundle bundle = new Bundle();
        bundle.putString("text", ALERT_TEXT);
        alert.show(fm,"fragment_alert");
    }
}
