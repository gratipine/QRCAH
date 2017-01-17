package com.endarpine.cahqr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.endarpine.cahqr.Logic.IntentIntegrator;
import com.endarpine.cahqr.Logic.IntentResult;

public class scanningCode extends AppCompatActivity {

    //todo: set timer on the scanning - gives the option to restart or go back if the scanning is taking too long
    public final static String EXTRA_MESSAGE = "Passed from Scanner";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_code);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
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
