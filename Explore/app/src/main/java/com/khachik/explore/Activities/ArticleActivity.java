package com.khachik.explore.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.khachik.explore.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ArticleActivity extends AppCompatActivity {

    private JSONArray json;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

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

        this.title = (TextView) findViewById(R.id.title);
        try {
            System.out.println(this.json.getJSONObject(0).getString("title"));
            this.title.setText(this.json.getJSONObject(0).getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
