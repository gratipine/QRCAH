package com.endarpine.cahqr;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.endarpine.cahqr.Logic.Facade;
import com.google.zxing.WriterException;

import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


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


    public void saveQRtoDevice ( View view){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder
                .setMessage(getString(R.string.message_saveTo))
                .setPositiveButton(getString(R.string.answer_externalStorage), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveQRtoDeviceExternal();
                    }
                })
                .setNegativeButton(getString(R.string.answer_internalStorage), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveQRtoDeviceInternal();
                    }
                });

        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }
    public boolean saveQRtoDeviceExternal (){
        if(isExternalStorageWritable()){
            //write to external storage
            TextView code = (TextView) findViewById(R.id.textView_addedCodeText);
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            String filename = (String) code.getText() + ".jpg";
            File file = new File(path, filename);
            OutputStream os = null;
            try {
                path.mkdirs();
                Bitmap QRBitmap = Facade.createQR(code.toString());
                os = new FileOutputStream(file);
                QRBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            } catch (WriterException e) {
                showAlert(e.getMessage());
            } catch (FileNotFoundException e) {
                showAlert(e.getMessage());
            }
            finally {
                try{
                    os.close();
                } catch (IOException e) {
                    showAlert(e.getMessage());
                }
            }
            return true;

        } else{
            //pass a message to the user informing them of inability to write in external
            return false;
        }


    }
    public boolean saveQRtoDeviceInternal (){
        //save message to internal storage
        TextView code = (TextView) findViewById(R.id.textView_addedCodeText);
        String filename = code.getText() + ".jpg";
        File path = getFilesDir();
        File file = new File(path, filename);
        OutputStream os = null;
        try {
            path.mkdirs();
            Bitmap QRBitmap = Facade.createQR(code.toString());
            os = new FileOutputStream(file);
            QRBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
        } catch (WriterException e) {
            showAlert(e.getMessage());
        } catch (FileNotFoundException e) {
            showAlert(e.getMessage());
        }
        finally {
            try{
                os.close();
            } catch (IOException e) {
                showAlert(e.getMessage());
            }
        }
        return true;
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private void showAlert(String exceptionName) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder
                .setMessage(getString(R.string.exceptionHandlerMessage1)+ exceptionName + getString(R.string.exceptionHandlerMessage2))
                .setPositiveButton(getString(R.string.answer_tryAgain), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //go back to previous activity and try again
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.answer_mainScreen), new DialogInterface.OnClickListener() {
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
