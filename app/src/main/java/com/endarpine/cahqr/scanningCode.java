package com.endarpine.cahqr;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.endarpine.cahqr.Logic.IntentIntegrator;
import com.endarpine.cahqr.Logic.IntentResult;

import java.util.Timer;
import java.util.TimerTask;

public class scanningCode extends AppCompatActivity {

    //todo: get internet connection on the phone and test this
    public final static String EXTRA_MESSAGE = "Passed from Scanner";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_code);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();

        //start timer which waits 10s before performing a task
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //if the timer runs out before we get result, display message asking Go back or Try again
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(scanningCode.this);
                alertBuilder
                        .setMessage(getString(R.string.delayMessageString))
                        .setPositiveButton(getString(R.string.tryAgain), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //restart activity
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(getString(R.string.goBack), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //go back
                                finish();
                            }
                        });

                AlertDialog dialog = alertBuilder.create();
                dialog.show();
            }
        }, 10000);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String message = scanResult.getContents();
            Intent toResults = new Intent(scanningCode.this,reader.class);
            toResults.putExtra(EXTRA_MESSAGE,message);
            startActivity(toResults);
        }
        else {

        }
    }
}
