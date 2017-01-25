package com.endarpine.cahqr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.endarpine.cahqr.Logic.Facade;
import com.google.zxing.WriterException;

import android.view.View;


public class codePicture extends AppCompatActivity {

    private static String message;
    private static final String ALERT_TEXT = "Uh oh, something went wrong";
    public static Bitmap QRcode;

    //todo: fix layout
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
            //todo:handle exception
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            //todo: handle this
            e.printStackTrace();
        }
    }

        public void saveQRtoDevice (View view){
            String description = "QR code created through CAHQR";
            MediaStore.Images.Media.insertImage(getContentResolver(),QRcode,message,description);
    }
}
