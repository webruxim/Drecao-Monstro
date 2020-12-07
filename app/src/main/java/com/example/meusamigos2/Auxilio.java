package com.example.meusamigos2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Evandro on 20/05/2017.
 */

public class Auxilio {
    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                    & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }

    public static String md5Hex (String message) {
        try {
            MessageDigest md =
                    MessageDigest.getInstance("MD5");
            return hex (md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public static Bitmap getImagemBytes(byte [] bytes){
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

    public static byte[] getImagemBytesFromUrl(String url){
        try{
            URL endereco = new URL(url);
            InputStream inputStream;
            final Bitmap imagem;
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            return byteArray;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verificaStringBase64(String string) {
        try {
            Base64.decode(string, 0);
            return true;
        } catch(IllegalArgumentException iae) {
            return false;
        }
    }

    public static String convertBitmapToString(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream); //compress to which format you want.
        byte[] byte_arr = stream.toByteArray();
        String imageStr = Base64.encodeToString(byte_arr, 1);
        return imageStr;
    }

    public static Bitmap convertStringToBitmap(String bmp) {
        byte[] imageAsBytes = Base64.decode(bmp.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

}




