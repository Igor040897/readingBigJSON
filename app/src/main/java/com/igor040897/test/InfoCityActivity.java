package com.igor040897.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.geonames.WikipediaArticle;

/**
 * Created by fanre on 9/30/2017.
 */

public class InfoCityActivity extends AppCompatActivity {

    private TextView tvCity;
    private TextView tvLongitude;
    private TextView tvLatitude;
    private TextView tvUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_city);

        tvCity = findViewById(R.id.city);
        tvLongitude = findViewById(R.id.longitude);
        tvLatitude = findViewById(R.id.latitude);
        tvUrl = findViewById(R.id.url);
    }


    @Override
    public void onResume() {
        super.onResume();

        try {
            String city = getIntent().getSerializableExtra("City").toString();
            WikipediaArticle wikipediaArticle = new GeoNamesTask().execute(city).get();

            tvCity.setText(getString(R.string.city) + wikipediaArticle.getTitle());
            tvLatitude.setText(getString(R.string.latitude) + String.valueOf(wikipediaArticle.getLatitude()));
            tvLongitude.setText(getString(R.string.longitude) + String.valueOf(wikipediaArticle.getLongitude()));
            tvUrl.setText(wikipediaArticle.getWikipediaUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(tvUrl.getText().toString()));
                startActivity(intent);
            }
        });
    }

}
