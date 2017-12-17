package com.khachik.explore.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.khachik.explore.R;
import com.khachik.explore.Requests.Requests;

import org.json.JSONArray;
import org.json.JSONException;

public class ArticleActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    GoogleMap googleMap;
    private JSONArray json;
    private ImageView wallpaper;
    private ImageView openGallery;
    private TextView title;
    private TextView buildingDate;
    private TextView country;
    private TextView city;
    private TextView content;
    private Requests request;
    private String lon;
    private String lat;
    private String title_name;
    private String imagesFolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("______________________________________________________________________");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        this.request = new Requests(this);
        this.wallpaper = (ImageView) findViewById(R.id.bg_image);
        this.openGallery = (ImageView) findViewById(R.id.open_gallery_icon);
        String resString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                resString= null;
            } else {
                resString= extras.getString("respons");
            }
        } else {
            resString = (String) savedInstanceState.getSerializable("respons");
        }

        try {
            this.json = new JSONArray(resString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        this.title = (TextView) findViewById(R.id.title);
        this.buildingDate = (TextView) findViewById(R.id.building_date);
        this.country = (TextView) findViewById(R.id.country);
        this.city= (TextView) findViewById(R.id.city);
        this.content= (TextView) findViewById(R.id.content);
        // request.getArticleWallpaper(this.wallpaper, "wallpaper.jpeg");


        try {
            System.out.println("JSON ---------- " + this.json.getJSONObject(0).toString());
            System.out.println(this.json.getJSONObject(0).getString("city"));
            this.title_name = this.json.getJSONObject(0).getString("title");
            this.imagesFolder = this.json.getJSONObject(0).getString("images_folder");
            this.title.setText(this.title_name);
            this.country.setText(this.json.getJSONObject(0).getString("country"));
            this.city.setText(this.json.getJSONObject(0).getString("city"));
            this.buildingDate.setText(this.json.getJSONObject(0).getString("building_date"));
            this.content.setText(this.json.getJSONObject(0).getString("data"));
            toolbar.setTitle(this.json.getJSONObject(0).getString("title")); // Toolbar title
            String wallpaper = this.json.getJSONObject(0).getString("wallpaper_image");
            this.lon = this.json.getJSONObject(0).getString("longitude");
            this.lat = this.json.getJSONObject(0).getString("latitude");
            System.out.println("latitude __________________" + this.lat);
            System.out.println("longitude__________________" + this.lon);
            if (!wallpaper.isEmpty()) {
                request.getArticleWallpaper(this.wallpaper, wallpaper);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


        this.openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticleActivity.this, GalleryActivity.class);
                intent.putExtra("title", title_name);
                intent.putExtra("images_folder", imagesFolder);
                ArticleActivity.this.startActivity(intent);
//                ArticleActivity.this.finish();
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Move the map's camera to the same location.
        // Add a marker
        // Zoom the camera
        LatLng loc = new LatLng(0,0);
        if(!(this.lat.equals("null") || this.lon.equals("null"))) {
            loc = new LatLng(Float.parseFloat(this.lat), Float.parseFloat(this.lon));
        }
        // LatLng loc = new LatLng(40.195186, 44.524703);
        try {
            googleMap.addMarker(new MarkerOptions().position(loc)
                    .title(this.json.getJSONObject(0).getString("title")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("Profile", "popping backstack");
            fm.popBackStack();

        } else {
            Log.i("Profile", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
}
