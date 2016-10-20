package com.example.android.minorproject;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ankit on 05-10-2016.
 */
    public class LoginConfig {
        //URL to our login.php file
        public static final String loginurl = "http://172.16.105.143/login.php";

        //Keys for enrollment and password as defined in our $_POST['key'] in login.php
        public static final String enrollmentkey = "enrollment";
        public static final String passwordkey = "password";

        //If server response is equal to this that means login is successful
        public static final String loginsuccess ="success";

        //Keys for Sharedpreferences
        //This would be the name of our shared preferences
        public static final String sharedprefname = "logininfo";

        //This would be used to store the enrollment of current logged in user
        public static final String enrollmentsharedpref = "enrollment";

        //We will use this to store the boolean in sharedpreference to track user is loggedin or not
        public static final String passwordsharedpref = "loggedin";
    }
