package com.khachik.explore.Requests;


import android.content.Context;
import android.net.http.RequestQueue;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.khachik.explore.Configs.Config;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;


import java.io.IOException;

public class Requests {
    private Config config;
    private String host;
    private int port;

    public Requests() {
        this.config = new Config();
        this.host = config.getHost();
        this.port = config.getPort();
    }

    public String getArticleById(int id) {
        String url = "192.168.4.221:4300/article/:1";


        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://192.168.4.221:4300/article:1");
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseStr = "";
        try {
            responseStr = client.execute(request, responseHandler);
            if(!responseStr.equalsIgnoreCase("")){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("_____________________________________" + responseStr);
        return responseStr;

    }
}
