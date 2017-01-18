package com.endarpine.cahqr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.endarpine.cahqr.Logic.Facade;
import com.google.zxing.WriterException;

public class codePicture extends AppCompatActivity {

    private static String message;
    private static final String ALERT_TEXT = "Uh oh, something went wrong";
    //todo: the message should be displayed together with the picture
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_picture);

        //getting the contents of the user input
        Intent intent = getIntent();
        message = intent.getStringExtra(addCode.EXTRA_MESSAGE);

        //encoding
        try {
            Bitmap code = Facade.createQR(message);
        } catch (WriterException e) {
            //todo:handle exception
            e.printStackTrace();
        }
        //todo:add a container for the QR code in the layout

    }

    private void showAlert() {
        FragmentManager fm = getSupportFragmentManager();
        Alert alert = new Alert();
        Bundle bundle = new Bundle();
        bundle.putString("text", ALERT_TEXT);
        alert.show(fm, "fragment_alert");
    }
}
