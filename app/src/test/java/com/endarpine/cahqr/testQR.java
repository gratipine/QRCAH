package com.endarpine.cahqr;


/**
 * Created by Galina on 11/28/2016.
 */
import android.graphics.Bitmap;

import com.endarpine.cahqr.Logic.QR;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.fail;

public class testQR {

    @Test
    public void testQRcreateInterpret(){
        String message = "Random String of a long length";
        Bitmap result;
        String answer=null;
        try {
            result = QR.encode(message);
            answer = QR.interpret(result);
        } catch (Exception e) {
            fail("Should be passing with no problem");
        }

        Assert.assertTrue(message==answer);
    }

}
