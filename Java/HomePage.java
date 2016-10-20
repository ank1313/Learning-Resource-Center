package com.example.android.minorproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ankit on 13-10-2016.
 */
public class HomePage extends AppCompatActivity {

    public static String enrollment3;
    SharedPreferences sharedPreferences;
    String enrollment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        sharedPreferences = getSharedPreferences(LoginConfig.sharedprefname, Context.MODE_PRIVATE);
        enrollment = sharedPreferences.getString(LoginConfig.enrollmentsharedpref, "Not Available");
        Intent intent = getIntent();
        enrollment3 = intent.getStringExtra(LoginActivity.checkenrollment);
        TextView textView = (TextView) findViewById(R.id.Welcome);
        textView.setText("Welcome " + enrollment);
    }

    public void viewdetails(View view) {
        Intent i = new Intent(HomePage.this, ViewDetailsActivity.class);
        i.putExtra(LoginActivity.checkenrollment,enrollment);
        Log.e("PRINT", "zzz");
        startActivity(i);
        finish();
    }

    public void searchbooks(View view){
        Intent i = new Intent(HomePage.this, SearchBooksActivity.class);
        startActivity(i);
        finish();
    }

    public void logout1(View view){
        logout();
    }


   /* public void logout(View view) {
        Intent i = new Intent(HomePage.this, LoginActivity.class);
        startActivity(i);
        finish();
    }*/

    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(LoginConfig.sharedprefname,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(LoginConfig.passwordsharedpref, false);

                        //Putting blank value to email
                        editor.putString(LoginConfig.enrollmentsharedpref, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(HomePage.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public void onBackPressed() {
        logout();
    }

}