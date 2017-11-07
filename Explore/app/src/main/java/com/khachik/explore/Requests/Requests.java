package com.khachik.explore.Requests;


import android.content.Context;
import android.content.Intent;
import android.net.http.RequestQueue;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khachik.explore.Activities.ArticleActivity;
import com.khachik.explore.Activities.MainActivity;
import com.khachik.explore.Activities.SplashActivity;
import com.khachik.explore.Configs.Config;
import com.khachik.explore.Fragments.ScanFragment;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;


import java.io.Console;
import java.io.IOException;

public class Requests {
    private Config config;
    private String host;
    private int port;
    Context context;
    private com.android.volley.RequestQueue queue;
    public Requests(Context context) {
        this.context = context;
        this.queue = Volley.newRequestQueue(context);
        this.config = new Config();
        this.host = config.getHost();
        this.port = config.getPort();
    }

    public void getArticleById(int id) {

        String url = "http://" + this.host + ":" + this.port + "/article/:" + id;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
                        // Display the first 500 characters of the response string.
                        System.out.println("Response is: " + res);
                        Intent intent = new Intent(context, ArticleActivity.class);
                        intent.putExtra("respons", res);
                        context.startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.

        this.queue.add(stringRequest);
    }
}
