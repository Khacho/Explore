package com.khachik.explore.Requests;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.RequestQueue;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.khachik.explore.Activities.ArticleActivity;
import com.khachik.explore.Activities.MainActivity;
import com.khachik.explore.Activities.SplashActivity;
import com.khachik.explore.Adapters.ArticlesAdapter;
import com.khachik.explore.Configs.Config;
import com.khachik.explore.Fragments.ScanFragment;
import com.khachik.explore.Models.ArticlesModel;
import com.khachik.explore.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.Console;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Requests {
    private Config config;
    private String host;
    private int port;
    Context context;
    private RecyclerView.Adapter adapter;
    private ArrayList<ArticlesModel> adapterItems;
    private com.android.volley.RequestQueue queue;
    public Requests(Context context) {
        this.context = context;
        this.queue = Volley.newRequestQueue(context);
        this.config = new Config();
        this.host = config.getHost();
        this.port = config.getPort();
    }

    public void getArticleById(String id) {

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

    public void getArticleByName(String title) throws UnsupportedEncodingException {

        String url = "http://" + this.host + ":" + this.port + "/articleByName/:" + URLEncoder.encode(title, "utf-8");
        // Request a string response from the provided URL.
//        System.out.println("Request is" + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
//                        System.out.println("Response is: " + res);
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

    public void getArticleWallpaper(final ImageView imageView, String path) {
        String url = "http://" + this.host + ":" + this.port + "/image?path=" +  path;
        System.out.println("_____reqest_____" + path);
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        System.out.println("success");
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error");
                    }
                });
        this.queue.add(request);
    }

    public void getArticleByCountry(String country, final View view) {
        String url = "http://" + this.host + ":" + this.port + "/articles/:" + country;
        // Request a string response from the provided URL.
        System.out.println("url - " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
                        // Display the first 500 characters of the response string.
                        System.out.println("______ Response is: " + res);
                        JSONArray resArray = null;
                        try {
                            resArray = new JSONArray(res);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterItems = new ArrayList<>();
                        for(int i=0;i<resArray.length();i++){
                            try {
                                String id = resArray.getJSONObject(i).getString("id");
                                String title = resArray.getJSONObject(i).getString("title");
                                String country = resArray.getJSONObject(i).getString("country");
                                String city = resArray.getJSONObject(i).getString("city");
                                String date = resArray.getJSONObject(i).getString("building_date");
                                String data = resArray.getJSONObject(i).getString("data");
                                String wallpaper_image = resArray.getJSONObject(i).getString("wallpaper_image");
                                String latitude = resArray.getJSONObject(i).getString("latitude");
                                String longitude = resArray.getJSONObject(i).getString("longitude");
                                String imagesFolder= resArray.getJSONObject(i).getString("images_folder");
                                adapterItems.add(new ArticlesModel(id, title, data, date, city, country, imagesFolder, wallpaper_image, latitude, longitude));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        setupRecyclerview(view, R.id.home_recyclerview);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(" _______ That didn't work!");
            }
        });
        // Add the request to the RequestQueue.

        this.queue.add(stringRequest);
    }

    public void getFavoriteArticles(ArrayList<String> idList, final View view){
        String url = "http://" + this.host + ":" + this.port + "/favorites?list[]=" + idList.get(0);
        for(int i = 1; i < idList.size(); ++i) {
            url += "&list[]=" + idList.get(i);
        }
        // Request a string response from the provided URL.
        System.out.println("url - " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
                        // Display the first 500 characters of the response string.
                        System.out.println("______ Response is: " + res);
                        JSONArray resArray = null;
                        try {
                            resArray = new JSONArray(res);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterItems = new ArrayList<>();
                        for(int i = 0; i < resArray.length(); i++){
                            try {
                                String id = resArray.getJSONObject(i).getString("id");
                                String title = resArray.getJSONObject(i).getString("title");
                                String country = resArray.getJSONObject(i).getString("country");
                                String city = resArray.getJSONObject(i).getString("city");
                                String date = resArray.getJSONObject(i).getString("building_date");
                                String data = resArray.getJSONObject(i).getString("data");
                                String wallpaper_image = resArray.getJSONObject(i).getString("wallpaper_image");
                                String latitude = resArray.getJSONObject(i).getString("latitude");
                                String longitude = resArray.getJSONObject(i).getString("longitude");
                                String imagesFolder= resArray.getJSONObject(i).getString("images_folder");
                                adapterItems.add(new ArticlesModel(id, title, data, date, city, country, imagesFolder, wallpaper_image, latitude, longitude));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        setupRecyclerview(view, R.id.favorites_recyclerview);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(" _______ That didn't work!");
            }
        });
        // Add the request to the RequestQueue.

        this.queue.add(stringRequest);
    }

    private void setupRecyclerview(View view, int rec) {
        RecyclerView recyclerView = view.findViewById(rec);
        adapter = new ArticlesAdapter(adapterItems, view.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    public String getUnicodeString(String myString) {
        String text = "";
        try {

            byte[] utf8Bytes = myString.getBytes("UTF8");
            text = new String(utf8Bytes, "UTF8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }
}
