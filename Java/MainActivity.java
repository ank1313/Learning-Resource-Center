package com.example.android.minorproject;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView registertextview;
    EditText firstname;
    EditText lastname;
    EditText enrollment;
    EditText password;
    Spinner branch;
    Spinner semester;
    Spinner gender;
    EditText email;
    EditText address;
    EditText pincode;
    EditText phone;
    EditText date;
    Button register;
    Button loginlinkscreen;

    String storebranch;
    String storesemester;
    String storegender;
    String[] branches = {"BRANCH","CSE", "ECE", "IT", "BIOTECH"};
    String[] semesters = {"SEMESTER","1st","2nd","3rd","4th","5th","6th","7th","8th"};
    String[] genders = {"GENDER","Male","Female","Others"};
    String year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        registertextview = (TextView) findViewById(R.id.registertextview);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        enrollment = (EditText) findViewById(R.id.enrollment);
        password = (EditText) findViewById(R.id.password);
        branch = (Spinner) findViewById(R.id.branch);
        semester = (Spinner) findViewById(R.id.semester);
        gender = (Spinner) findViewById(R.id.gender);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        pincode = (EditText) findViewById(R.id.pincode);
        phone = (EditText) findViewById(R.id.phone);
        date = (EditText) findViewById(R.id.date);
        register = (Button) findViewById(R.id.register);
        loginlinkscreen = (Button) findViewById(R.id.loginscreenlink);

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String firstname1 = firstname.getText().toString().trim();
                String lastname1 = lastname.getText().toString().trim();
                String enrollment1 = enrollment.getText().toString().trim();
                String password1 = password.getText().toString().trim();
                String email1 = email.getText().toString().trim();
                String address1 = address.getText().toString().trim();
                String pincode1 = pincode.getText().toString().trim();
                String phone1 = phone.getText().toString().trim();
                String date1 = date.getText().toString().trim();
          //      Log.e("SDADS",storebranch);

                if(storebranch.equals("BRANCH"))
                    Toast.makeText(getApplicationContext(),"Please Enter Your Branch!",Toast.LENGTH_LONG).show();
                else if(storebranch.equals("SEMESTER"))
                    Toast.makeText(getApplicationContext(),"Please Enter Your Semester!",Toast.LENGTH_LONG).show();
                else if(storebranch.equals("GENDER"))
                    Toast.makeText(getApplicationContext(),"Please Enter Your Gender!",Toast.LENGTH_LONG).show();
                else if(enrollment1.length()!=8)
                    Toast.makeText(getApplicationContext(),"Please Enter Correct Enrollment Number!",Toast.LENGTH_LONG).show();
                else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches())
                    Toast.makeText(getApplicationContext(),"Please Enter Correct Email!",Toast.LENGTH_LONG).show();
                else if(pincode1.length()!=6)
                    Toast.makeText(getApplicationContext(),"Please Enter Correct Pincode!",Toast.LENGTH_LONG).show();
                else if(!isvaliddate(date1))
                    Toast.makeText(getApplicationContext(),"Please Enter Correct Date!",Toast.LENGTH_LONG).show();

                else if (!firstname1.isEmpty() && !lastname1.isEmpty() && !enrollment1.isEmpty() && !password1.isEmpty() && !email1.isEmpty() && !address1.isEmpty() && !pincode1.isEmpty() && !phone1.isEmpty() && !date1.isEmpty() &&!storebranch.isEmpty() && !storesemester.isEmpty() && !storegender.isEmpty()) {
                 insertToDatabase(firstname1,lastname1,enrollment1,password1,email1,address1,pincode1,phone1,date1,storebranch,storesemester,storegender);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
                }
            }
        });

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, branches);
        branch.setAdapter(adapter1);
        branch.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = branch.getSelectedItemPosition();
                        storebranch = branch.getSelectedItem().toString();
                        //           Log.e("ASDASLKDASKLD", storebranch);
               //         Toast.makeText(getApplicationContext(), "You have selected " + branches[+position], Toast.LENGTH_LONG).show();
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }

                }
        );

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semesters);
        semester.setAdapter(adapter2);
        semester.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = semester.getSelectedItemPosition();
                        storesemester = semester.getSelectedItem().toString();
                        //           Log.e("ASDASLKDASKLD", storebranch);
                //        Toast.makeText(getApplicationContext(), "You have selected " + semesters[+position], Toast.LENGTH_LONG).show();
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                }
        );

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genders);
        gender.setAdapter(adapter3);
        gender.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = gender.getSelectedItemPosition();
                        storegender = gender.getSelectedItem().toString();
                        //           Log.e("ASDASLKDASKLD", storebranch);
                //        Toast.makeText(getApplicationContext(), "You have selected " + genders[+position], Toast.LENGTH_LONG).show();
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }

                }
        );

/*        email = (EditText) findViewById(R.id.email);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        loginscreenlink = (Button) findViewById(R.id.loginscreenlink);

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.e("SDSADASDA", "DDD");
                String Name = name.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                Log.e("iuiiu",Name+" "+Email+" "+Password);
                if (!Name.isEmpty() && !Email.isEmpty() && !Password.isEmpty()) {
                    insertToDatabase(Name, Email, Password);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void insertToDatabase(String name, String email,  String password) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String Name = params[0];
                String Email = params[1];
                String Password = params[2];
                // String Yipee = params[3];
                //    Log.e("sdasdsdasdad",Name+" "+Email+" "+Yipee);
                //           Log.e("Param0: ",paramUsername);
                //         Log.e("Param1: ",paramAddress);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name",Name));
                nameValuePairs.add(new BasicNameValuePair("email",Email));
                nameValuePairs.add(new BasicNameValuePair("password",Password));
-
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost(
                            "http://172.16.99.166/insert-db.php");
                             System.out.println("asdjajdhjad");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    System.out.println(Name + Email +Password);
                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                //           System.out.println(nameValuePairs);
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(getApplicationContext(),"SUCCCCCCEEEESSS",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                textViewResult.setText("Inserted");
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name,email,password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
*/
    }

    public boolean isvaliddate(String date1){
  /*      char[] date = date1.toCharArray();
        char[] year1 = year.toCharArray();
        if(date1.length()!=10)
            return false;
        else if(((int)(date[0]-'0')>3) || (date[2]!='/') || ((int)(date[3]-'0')>2) || (date[5]!='/') || ((int)(date[6]-'0')>2) || ((int)(date[6]-'0')==0))
            return false;
        else if((date1.substring(6,9)).toCharArray()
        return true;*/
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();
        Log.e("ANKIT",df.format(dateobj));
        year = df.format(dateobj);

        if(Integer.parseInt(date1.substring(6,9))>Integer.parseInt(year.substring(6,9)))
            return false;
        else if (Integer.parseInt(date1.substring(6,9)) == Integer.parseInt(year.substring(6,9)))
        {
            if(Integer.parseInt(date1.substring(3,4))>Integer.parseInt(year.substring(3,4)))
                return false;
            else if(Integer.parseInt(date1.substring(3,4))==Integer.parseInt(year.substring(3,4)))
            {
                if(Integer.parseInt(date1.substring(0,1))>Integer.parseInt(year.substring(0,1)))
                    return false;
                else
                    return true;
            }
            else
                return true;
        }
        else
            return true;
    }

    public void gotologinscreen(View view){
        Intent i = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void insertToDatabase(String firstname1, String lastname1,  String enrollment1,String password1,String email1,String address1,String pincode1,String phone1,String date1,String storebranch1,String storesemester1,String storegender1) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String firstname2 = params[0];
                String lastname2 = params[1];
                String enrollment2 = params[2];
                String password2 = params[3];
                String email2 = params[4];
                String address2 = params[5];
                String pincode2 = params[6];
                String phone2 = params[7];
                String date2 = params[8];
                String storebranch2 = params[9];
                String storesemester2 = params[10];
                String storegender2 = params[11];

                // String Yipee = params[3];
                //    Log.e("sdasdsdasdad",Name+" */"+Email+" "+Yipee);
                //           Log.e("Param0: ",paramUsername);
                //         Log.e("Param1: ",paramAddress);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("firstname",firstname2));
                nameValuePairs.add(new BasicNameValuePair("lastname",lastname2));
                nameValuePairs.add(new BasicNameValuePair("enrollment",enrollment2));
                //ansdasd
                password2 = new Encrypt().Encrypted(password2);
                nameValuePairs.add(new BasicNameValuePair("password",password2));
                nameValuePairs.add(new BasicNameValuePair("branch",storebranch2));
                nameValuePairs.add(new BasicNameValuePair("semester",storesemester2));
                nameValuePairs.add(new BasicNameValuePair("gender",storegender2));
                nameValuePairs.add(new BasicNameValuePair("email",email2));
                nameValuePairs.add(new BasicNameValuePair("address",address2));
                nameValuePairs.add(new BasicNameValuePair("pincode",pincode2));
                nameValuePairs.add(new BasicNameValuePair("phone",phone2));
                nameValuePairs.add(new BasicNameValuePair("dateofbirth",date2));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://172.16.105.143/insert-db.php");
           //         System.out.println("asdjajdhjad");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
          //          System.out.println(Name + Email +Password);
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                //           System.out.println(nameValuePairs);
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(getApplicationContext(),"SUCCCCCCEEEESSS",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                textViewResult.setText("Inserted");
                Intent i1 = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i1);
                finish();
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(firstname1,lastname1,enrollment1,password1,email1,address1,pincode1,phone1,date1,storebranch1,storesemester1,storegender1);
    }
}