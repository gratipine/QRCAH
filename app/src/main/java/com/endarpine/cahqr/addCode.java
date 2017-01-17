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
            //todo: if it is null, a message prompts the user to add something on the field
            showAlert();
        } else {
            //todo: add same method for getting the message as the scanning activity
            Intent intent = new Intent(this, codePicture.class);
            startActivity(intent);
        }

    }
    private void showAlert(){
        FragmentManager fm = getSupportFragmentManager();
        Alert alert = new Alert();
        alert.show(fm,"fragment_alert");
    }
}
