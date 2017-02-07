package com.endarpine.cahqr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;



import com.endarpine.cahqr.Logic.Facade;

//source:https://www.tutorialspoint.com/android/android_text_to_speech.htm
public class reader extends AppCompatActivity {
    Bitmap QR;
    TextToSpeech t1;
    public static Boolean showsText = Boolean.FALSE;
    public static Boolean readsText = Boolean.TRUE;
    private static String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the up/down keys to affect the media volume, not ringer
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.activity_reader);
        //getting the contents of the QR from the scanner
        Intent intent = getIntent();
        message = intent.getStringExtra(scanningCode.EXTRA_MESSAGE);
        if(readsText){
            readOutLoud();
            Button repeat = (Button)findViewById(R.id.button_repeatReadingOutLoud);
            repeat.setVisibility(View.VISIBLE);
        }
        if(showsText){
            TextView textView = (TextView)findViewById(R.id.textView_readCodeText);
            textView.setText(message);
        }
    }

    @Override
    protected void onPause (){
        if(t1!=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
    public void setVisibility(View view){
        CheckBox visible = (CheckBox)findViewById(R.id.checkBox_textVisibility);
        showsText = visible.isChecked();
    }

    public void setReadOutLoud(View view) {
        CheckBox outLoud = (CheckBox)findViewById(R.id.checkBox_soundAvailable);
        readsText = outLoud.isChecked();
    }

    public void readOutLoud(){

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
}
