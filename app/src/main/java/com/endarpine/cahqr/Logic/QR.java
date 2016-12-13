package com.endarpine.cahqr.Logic;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by Galina on 11/28/2016.
 */
//source: http://stackoverflow.com/questions/8800919/how-to-generate-a-qr-code-for-an-android-application
import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData;
import java.*;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;


public class QR {

    public static String path=")";
    public static Bitmap encode(String message) throws WriterException{
        QRCodeWriter writer = new QRCodeWriter();
        int size = 512;
        BitMatrix bitMatrix = writer.encode(message, BarcodeFormat.QR_CODE, size, size);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++) {
                bmp.setPixel(i, j, bitMatrix.get(i,j) ? Color.BLACK:Color.WHITE);
            }
        }
        return bmp;
    }
    public static String interpret(Bitmap image) {

        return "0";
    }

    public static void currentPath (){
        QR object=new QR();
        path=String.format("%s/%s", System.getProperty("user.dir"), object.getClass().getPackage().getName().replace(".", "/"));
    }
}
