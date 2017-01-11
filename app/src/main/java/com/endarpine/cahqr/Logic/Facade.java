package com.endarpine.cahqr.Logic;

import android.graphics.Bitmap;
import com.google.zxing.WriterException;

/**
 * Created by Galina on 12/5/2016.
 */

//TODO: the facade pulls together the UI and the Logic -> should implement the exchange
public class Facade {
    public static String getQRMessage(Bitmap qr) {
        return com.endarpine.cahqr.Logic.QR.interpret(qr);
    }

    public static Bitmap createQR(String toread) throws WriterException {
        return com.endarpine.cahqr.Logic.QR.encode(toread);
    }
}
