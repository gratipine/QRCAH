package com.endarpine.cahqr;

import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //todo:refactor the names of the strings and buttons to be similar (UI)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.activity_main);
    }

    public void scanCode(View view) {
        Intent intent = new Intent(this, com.endarpine.cahqr.scanningCode.class);
        startActivity(intent);
    }

    public void addCode(View view) {
        Intent intent = new Intent(this, addCode.class);
        startActivity(intent);
    }


    public void about(View view) {
        Intent intent = new Intent(this, about.class);
        startActivity(intent);
    }

    public void settings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
