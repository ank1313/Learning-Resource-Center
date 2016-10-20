package com.example.android.minorproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ankit on 20-10-2016.
 */
public class UpdatePassword extends AppCompatActivity {
    private EditText passwordold;
    private EditText passwordnew;

    private String passwordold1;
    private String passwordnew1;
    private String enrollment = ViewDetailsActivity.enrollment;
    private String url="http://172.16.105.143/updatepassword.php";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepassword);
        passwordold = (EditText) findViewById(R.id.passwordold);
        passwordnew = (EditText) findViewById(R.id.passwordnew);
    }

    public void updatethepassword(View view)
    {
        passwordold1 = passwordold.getText().toString();
        passwordold1 = new Encrypt().Encrypted(passwordold1);
        passwordnew1 = passwordnew.getText().toString();
        passwordnew1 = new Encrypt().Encrypted(passwordnew1);
        update(enrollment, passwordold1, passwordnew1);
    }

    private void update(final String enrollment,final String passwordold1,final String passwordnew1)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //response=response.trim();
                        Log.e("resp", response);
                        if(response.equals("success"))
                        {
                            Toast.makeText(UpdatePassword.this,"Password Updated Successfully!",Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(UpdatePassword.this, "Could Not Update Password", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error","dasd");
                        Toast.makeText(UpdatePassword.this, "Error Occurred!", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                Log.e("Enrollment", enrollment+passwordold1+passwordnew1);
                params.put("enrollment",enrollment);
                params.put("passwordold",passwordold1);
                params.put("passwordnew",passwordnew1);
                //returning par
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void onBackPressed() {
        Intent i = new Intent(UpdatePassword.this,ViewDetailsActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
