package com.endarpine.cahqr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Locale;


import android.content.Intent;
import android.graphics.Bitmap;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.endarpine.cahqr.Logic.Facade;
import com.endarpine.cahqr.R;

import java.util.Locale;

//source:https://www.tutorialspoint.com/android/android_text_to_speech.htm
public class reader extends AppCompatActivity {

    //todo: add a bunch of ifs to handle showing text, not showing text and so on - to be done in the onCreate
    Bitmap QR;
    TextToSpeech t1;
    private static String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.activity_reader);
        //getting the contents of the QR from the scanner
        Intent intent = getIntent();
        message = intent.getStringExtra(scanningCode.EXTRA_MESSAGE);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });
        String stringToSpeak = Facade.getQRMessage(QR);
        Toast.makeText(getApplicationContext(), stringToSpeak,Toast.LENGTH_SHORT).show();
        //todo: figure out something not deprecated
        t1.speak(stringToSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onPause (){
        if(t1!=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}
