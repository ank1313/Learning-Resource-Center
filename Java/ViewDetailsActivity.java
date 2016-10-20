package com.example.android.minorproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ankit on 14-10-2016.
 */
public class ViewDetailsActivity extends AppCompatActivity {

    TextView viewdetails;
    public static String enrollment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdetails);
        Intent intent = getIntent();
        viewdetails = (TextView) findViewById(R.id.showdetails);
        enrollment = intent.getStringExtra(LoginActivity.checkenrollment);
        Log.e("KKK",enrollment);
        getData();
    }

    private void getData() {
//        String id = editTextId.getText().toString().trim();
//        if (id.equals("")) {
//            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
//            return;
            String url = "http://172.16.105.143/retrievedata.php?enrollment="+enrollment;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("PRINT","OPOPOPOPPPP");
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewDetailsActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String firstname="";
        String lastname="";
        String password="";
        String enrollment="";
        String branch="";
        String semester="";
        String gender="";
        String email="";
        String address="";
        String pincode="";
        String phone="";
        String dateofbirth="";
        try {
            Log.e("object",response);
           // JSONObject jsonObject = new JSONObject(response);
            //JSONArray result = jsonObject.getJSONArray("result");

            JSONArray result = new JSONArray(response);
            Log.e("array",result+"");
            JSONObject userdata = result.getJSONObject(0);
            firstname = userdata.getString("firstname");
            lastname = userdata.getString("lastname");
            password = userdata.getString("password");
            enrollment = userdata.getString("enrollment");
            branch = userdata.getString("branch");
            semester = userdata.getString("semester");
            gender = userdata.getString("gender");
            email = userdata.getString("email");
            address = userdata.getString("address");
            pincode = userdata.getString("pincode");
            phone = userdata.getString("phone");
            dateofbirth = userdata.getString("dateofbirth");
            Log.e("PRINT",phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        viewdetails.setText("Firstname:\t" + firstname + "\nLastname:\t" + lastname + "\nEnrollment:\t" + enrollment + "\nPassword:\t" + password + "\nBranch:\t" + branch + "\nSemester:\t" + semester + "\nGender:\t" + gender + "\nEmail:\t" + email + "\nAddress:\t" + address + "\nPincode:\t" + pincode + "\nPhone:\t" + phone + "\nDateofbirth:\t" + dateofbirth);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ViewDetailsActivity.this,HomePage.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }

    public void updatedetails(View view){
        Intent i =new Intent(ViewDetailsActivity.this,UpdateDetails.class);
        startActivity(i);
       // finish();
    }

    public void updatepassword(View view){
        Log.e("OPEN","OPEN");
        Intent i =new Intent(ViewDetailsActivity.this,UpdatePassword.class);
        startActivity(i);
        //finish();
    }

//    public void displaydetails(View v) {
//        Log.e("PRINT","PLEASEEE");
//        getData();
//    }
}

