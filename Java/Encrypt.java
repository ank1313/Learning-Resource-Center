package com.example.android.minorproject;

import com.mobapphome.mahencryptorlib.MAHEncryptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ankit on 21-10-2016.
 */
public class Encrypt {
    public String Encrypted(String s)
    {
        String encrypted="";
        try {
            MAHEncryptor mahEncryptor = MAHEncryptor.newInstance("We Have Made Our Minor");
            encrypted = mahEncryptor.encode(s);
            }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }
        return encrypted;
    }
    public String Decrypt(String s)
    {
        String decrypted="";
        try {
            MAHEncryptor mahEncryptor = MAHEncryptor.newInstance("We Have Made Our Minor");
             decrypted = mahEncryptor.decode(s);
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }
        return decrypted;
    }
}
