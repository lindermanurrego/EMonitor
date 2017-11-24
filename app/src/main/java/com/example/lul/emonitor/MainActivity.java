package com.example.lul.emonitor;

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
    public void onEqsDownloaded(String eqsData) {
        ArrayList<Earthquake> eqList = new ArrayList<>();

        try {
            JSONObject  jsonObject = new JSONObject(eqsData);
            JSONArray   featuresJsonArray = jsonObject.getJSONArray("features");

            for (int i=0;i<featuresJsonArray.length();i++){
                JSONObject featuresJsonObject = featuresJsonArray.getJSONObject(i);
                JSONObject propertiesJsonObject = featuresJsonObject.getJSONObject("properties");
                Double magnitud = propertiesJsonObject.getDouble("mag");
                String place = propertiesJsonObject.getString("place");
                eqList.add(new Earthquake(magnitud,place));
                Log.d("Manzana",magnitud + ";" + place);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        final EqAdapter eqAdapter = new EqAdapter(this,R.layout.eq_list_item,eqList);
        earthquakeListView.setAdapter(eqAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
            }
        });



        Log.d(eqsData,"s");
    }
}
