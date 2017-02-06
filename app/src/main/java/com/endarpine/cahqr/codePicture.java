package com.endarpine.cahqr;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.endarpine.cahqr.Logic.Facade;
import com.google.zxing.WriterException;

import android.view.View;


public class codePicture extends AppCompatActivity {

    private static String message;
    public static Bitmap QRcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_picture);

        //getting the contents of the user input
        Intent intent = getIntent();
        message = intent.getStringExtra(addCode.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView_addedCodeText);
        textView.setText(message);

        //encoding
        try {
            QRcode = Facade.createQR(message);
            ImageView codeImage = (ImageView)findViewById(R.id.imageView_addedCodeImage);
            codeImage.setImageBitmap(QRcode);
        } catch (WriterException e) {
            showAlert(e.getMessage());
        } catch (IllegalArgumentException e) {
            showAlert(e.getMessage());
        }
    }

    //todo: figure out how to save it to the device
    public void saveQRtoDevice (View view){
        String description = getString(R.string.internalCodePictureDescription);
        MediaStore.Images.Media.insertImage(getContentResolver(),QRcode,message,description);
    }

    private void showAlert(String exceptionName) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder
                .setMessage(getString(R.string.exceptionHandlerMessage1)+ exceptionName + getString(R.string.exceptionHandlerMessage2))
                .setPositiveButton(getString(R.string.tryAgain), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //go back to previous activity and try again
                        finish();
                    }
                })
                //todo: double check the intent creation with class.this
                .setNegativeButton(getString(R.string.mainScreen), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //go back to main screen
                        Intent intent = new Intent(codePicture.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }
}
