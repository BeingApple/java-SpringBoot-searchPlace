package com.beingapple.webservice.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SHA256Util {
    public static String getEncrypted(String plainText, String salt) throws NoSuchAlgorithmException{
        return getEncrypted(plainText, salt.getBytes());
    }

    public static String getEncrypted(String plainText, byte[] salt) throws NoSuchAlgorithmException {
        byte[] textBytes = plainText.getBytes();
        byte[] bytes = new byte[textBytes.length + salt.length];

        System.arraycopy(textBytes, 0, bytes, 0, textBytes.length);
        System.arraycopy(salt, 0, bytes, textBytes.length, salt.length);

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bytes);

        byte[] byteData = messageDigest.digest();

        StringBuffer stringBuffer = new StringBuffer();
        for(byte data : byteData){
            stringBuffer.append(Integer.toString((data & 0xFF) + 256, 16).substring(1));
        }

        return stringBuffer.toString();
    }

    public static String generateSalt(){
        Random random = new Random();

        byte[] salt = new byte[8];
        random.nextBytes(salt);

        StringBuffer stringBuffer = new StringBuffer();
        for(byte data : salt){
            stringBuffer.append(String.format("%02x", data));
        }

        return stringBuffer.toString();
    }
}
