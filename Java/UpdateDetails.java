package com.example.android.minorproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
public class UpdateDetails extends AppCompatActivity {

    private EditText email;
    private EditText address;
    private EditText pincode;
    private EditText phone;

    private String email1;
    private String address1;
    private String pincode1;
    private String phone1;
    private String enrollment=ViewDetailsActivity.enrollment;
    private String url="http://172.16.105.143/updatedetails.php";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedetails);
        email = (EditText) findViewById(R.id.email1);
        address = (EditText) findViewById(R.id.address1);
        pincode = (EditText) findViewById(R.id.pincode1);
        phone = (EditText) findViewById(R.id.phone1);
    }

    public void updatethedetails(View view)
    {
        email1 = email.getText().toString();
        address1 = address.getText().toString();
        pincode1 = pincode.getText().toString();
        phone1 = phone.getText().toString();
        Log.e("Phone1",phone1+ email1+ address1 +" "+ pincode1);
        update(enrollment, email1, address1, pincode1, phone1);
    }

    private void update(final String enrollment,final String email1,final String address1,final String pincode1,final String phone1){
        Log.e("UPDATE","ZZXXZZ");
    StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("taghj", "yash");
                    //response=response.trim();
                    Log.e("resp", response);
     //               System.out.print(response+"hello");
                    if(response.equals("success"))
                    {
                        Toast.makeText(UpdateDetails.this,"Details Updated Successfully!",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(UpdateDetails.this, "Could Not Update Details", Toast.LENGTH_LONG).show();
                    }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error","Dfsd");
                Toast.makeText(UpdateDetails.this, "Error Occurred!", Toast.LENGTH_LONG).show();
                }
            }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<>();
            //Adding parameters to request
            Log.e("Enrollment", enrollment);
            params.put("enrollment",enrollment);
            params.put("email",email1);
            params.put("address",address1);
            params.put("pincode",pincode1);
            params.put("phone",phone1);
            //returning par
            return params;
        }
    };

    //Adding the string request to the queue
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);
}

    public void onBackPressed() {
        Intent i = new Intent(UpdateDetails.this,ViewDetailsActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }

}
