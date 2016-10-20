package com.example.android.minorproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ankit on 17-10-2016.
 */

public class SearchBooksActivity extends AppCompatActivity{
    int m=0;
    private EditText searchbooks;
    private String string="";
  //  private TextView bookdata;

    private List<BookInfo> bookList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BooksAdapter bAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbooks);
      //  Intent intent = getIntent();
        searchbooks = (EditText) findViewById(R.id.searchbooks);
//        bookdata = (TextView) findViewById(R.id.bookdata);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        bAdapter = new BooksAdapter(bookList);
        RecyclerView.LayoutManager bLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(bLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bAdapter);
        Log.e("HELP","HELP");
    }

    public void getbooks(View view)
    {
        string  = searchbooks.getText().toString().trim();
        //string = string+String.valueOf(c);
        //getData();
        //string = string+String.valueOf(c);
        Log.e("NNN", string);
        getData();
    }

    private void getData() {
        Log.e("JJJ",string);
//        String id = editTextId.getText().toString().trim();
//        if (id.equals("")) {
//            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
//            return;
        string=string.replace(" ", "%20");
        String url = "http://172.16.105.143/searchbooks.php?string="+string;
        Log.e("url",url);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("PRINT", response);
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SearchBooksActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
     //   String bid = "";
        bookList.clear();
        String title = "";
        String author ="";
        String publisher="";
        String available="";
        try {
            Log.e("object", response);
            // JSONObject jsonObject = new JSONObject(response);
            //JSONArray result = jsonObject.getJSONArray("result");
            JSONArray result = new JSONArray(response);
            Log.e("LENGTH",result.length()+"");
            Log.e("array", result + "");
      //      bookdata.setText("");
            for(int i=0;i<result.length();i++) {
                JSONObject userdata = result.getJSONObject(i);
                Log.e("OUTPUT", userdata + "");
                title = userdata.getString("title");
                author = userdata.getString("author");
                publisher = userdata.getString("publisher");
                available = userdata.getString("available");
                Log.e("AVAIL", available);
                BookInfo book = new BookInfo(title,author,publisher,"Available:"+available);
                bookList.add(book);
                Log.e("BOOKSSS", bookList + "");
       //         bookdata.append("Title:\t" + title + "\nAuthor:\t" + author + "\nPublisher:\t" + publisher + "\nAvailable:\t" + available + "\n\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SearchBooksActivity.this,HomePage.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}