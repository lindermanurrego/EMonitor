package com.example.lul.emonitor;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

       if(Utils.isNetworwAvailable(this)) {
         downloadEarthQuakes();
       }else{
           //Leer la base de datos
          getEarthQuakesFromDb();
       }

    }

    private void getEarthQuakesFromDb(){
        EqDbHelper eqDbHelper = new EqDbHelper(this);
        SQLiteDatabase database  = eqDbHelper.getReadableDatabase();

        Cursor cursor = database.query(EqContract.EqColumns.TABLE_NAME,null,null,null,null,null,null);
        ArrayList<Earthquake> eqList = new ArrayList<>();


        while (cursor.moveToNext()){
            Double magnitude = cursor.getDouble(EqContract.EqColumns.MAGNITUDE_INDEX);
            String place = cursor.getString(EqContract.EqColumns.PLACE_INDEX) ;
            Long time = cursor.getLong(EqContract.EqColumns.TIME_INDEX);
            String longitude = cursor.getString(EqContract.EqColumns.LONGITUDE_INDEX);
            String latitude = cursor.getString(EqContract.EqColumns.LATITUDE_INDEX);

            eqList.add(new Earthquake(magnitude,place,time,longitude,latitude));

        }
        cursor.close();
        fillEqList(eqList);
    }
    private void downloadEarthQuakes(){
        DownloadEqAsyncTask downloadEqAsyncTask = new DownloadEqAsyncTask(this);
        downloadEqAsyncTask.delegate = this;
        try {
            downloadEqAsyncTask.execute(new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onEqsDownloaded(ArrayList<Earthquake> eqList) {
         fillEqList(eqList);
    }

    private void fillEqList(ArrayList<Earthquake> eqList){
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
