package com.example.lul.emonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DownloadEqAsyncTask.DownloadEqsInterface {
    private ListView earthquakeListView;
    public  final static String SELECT_EARTHQUAKE = "SELECTED_EARTHQUAKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       earthquakeListView = findViewById(R.id.earthquake_list_view);
        DownloadEqAsyncTask  downloadEqAsyncTask = new DownloadEqAsyncTask();
        downloadEqAsyncTask.delegate = this;
        try {
            downloadEqAsyncTask.execute(new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onEqsDownloaded(ArrayList<Earthquake> eqList) {

        final EqAdapter eqAdapter = new EqAdapter(this,R.layout.eq_list_item,eqList);
        earthquakeListView.setAdapter(eqAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

                Earthquake selectedEarthquake = eqAdapter.getItem(posicion);
                Intent intent = new Intent(MainActivity.this,detailActivity.class);
                intent.putExtra(SELECT_EARTHQUAKE, selectedEarthquake);
                startActivity(intent);
            }
        });
    }
}
