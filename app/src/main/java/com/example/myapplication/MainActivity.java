package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView myTextView = findViewById(R.id.myText);
        final TextView arrayText = findViewById(R.id.arrayJson);
        final EditText myEditText = findViewById(R.id.editTextTextPersonName);
        final EditText myEditText2 = findViewById(R.id.editTextTextPersonName2);
        Button myButton = findViewById(R.id.button);
        Button myButton2 = findViewById(R.id.button2);
        Button buttonDelete = findViewById(R.id.buttonDelete);


        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String url = "http://62.109.31.160:8088/main/";
        final JsonArrayRequest requestAll = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                       arrayText.setText(response.toString());
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        arrayText.setText(error.toString());
                    }
                }
        );
        final JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url+myEditText.getText(),
                null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        myTextView.setText(response.toString());
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        myTextView.setText(error.toString());
                    }
                }
        );

        requestQueue.add(objectRequest);
        requestQueue.add(requestAll);


        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final JsonObjectRequest requestOne = new JsonObjectRequest(
                        Request.Method.GET,
                        url+myEditText.getText(),
                        null,
                        new Response.Listener<JSONObject>(){
                            @Override
                            public void onResponse(JSONObject response) {
                                myTextView.setText(response.toString());
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                myTextView.setText(error.toString());
                            }
                        }
                );
                requestQueue.add(requestOne);
                requestQueue.add(requestAll);
            }
        };

        View.OnClickListener oclBtnOk2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest requestAdd = null;
                try {
                    requestAdd = new JsonObjectRequest(
                            Request.Method.POST,
                            url,
                            new JSONObject("{text:'"+myEditText2.getText()+"'}"),
                            new Response.Listener<JSONObject>(){
                                @Override
                                public void onResponse(JSONObject response) {
                                    myTextView.setText(response.toString());
                                }
                            },
                            new Response.ErrorListener(){
                                @Override
                                public void onErrorResponse(VolleyError error){
                                    myTextView.setText(error.toString());
                                }
                            }
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue.add(requestAdd);
                requestQueue.add(requestAll);
            }
        };

        View.OnClickListener oclBtnDl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest requestDelete = null;
                requestDelete = new JsonObjectRequest(
                        Request.Method.DELETE,
                        url+myEditText.getText(),
                        null,
                        new Response.Listener<JSONObject>(){
                            @Override
                            public void onResponse(JSONObject response) {
                                myTextView.setText(response.toString());
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                myTextView.setText(error.toString());
                            }
                        }
                );
                requestQueue.add(requestDelete);
                requestQueue.add(requestAll);
            }
        };
        myButton.setOnClickListener(oclBtnOk);
        myButton2.setOnClickListener(oclBtnOk2);
        buttonDelete.setOnClickListener(oclBtnDl);
    }
}