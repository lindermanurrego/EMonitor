package com.example.lul.emonitor;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by User on 18/11/2017.
 */

public class DownloadEqAsyncTask extends AsyncTask<URL,Void,ArrayList<Earthquake>> {
    String eqData = "";
    public DownloadEqsInterface delegate;

    public interface DownloadEqsInterface{
        void onEqsDownloaded(ArrayList<Earthquake> eqList);
    }

    @Override
    protected ArrayList<Earthquake> doInBackground(URL...urls) {
        ArrayList<Earthquake> eqList = null;
        try{
            eqData = downloadData(urls[0]);
            eqList = parseDataFromJson(eqData);
        }catch (IOException e){
            e.printStackTrace();
        }
        return eqList ;
    }

    @Override
    protected void onPostExecute(ArrayList<Earthquake> eqList) {

        super.onPostExecute(eqList);
        delegate.onEqsDownloaded(eqList);
    }

    private String downloadData(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e){
        }finally {

            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }

        }

        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();

        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8") );
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line =  bufferedReader.readLine();

            while( line != null){
                output.append(line);
                line = bufferedReader.readLine();
            }

        }
        return output.toString();
    }

    public ArrayList<Earthquake> parseDataFromJson(String eqsData) {
        ArrayList<Earthquake> eqList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(eqsData);
            JSONArray featuresJsonArray = jsonObject.getJSONArray("features");

            for (int i=0;i<featuresJsonArray.length();i++){
                JSONObject featuresJsonObject = featuresJsonArray.getJSONObject(i);
                JSONObject propertiesJsonObject = featuresJsonObject.getJSONObject("properties");
                Double magnitud = propertiesJsonObject.getDouble("mag");
                String place = propertiesJsonObject.getString("place");
                Long time = propertiesJsonObject.getLong("time");

                JSONObject geometryJsonObject = featuresJsonObject.getJSONObject("geometry");
                JSONArray  geometryJsonArray  = geometryJsonObject.getJSONArray("coordinates");
                String longitude = geometryJsonArray.getString(0);
                String latitude = geometryJsonArray.getString(1);

                eqList.add(new Earthquake(magnitud,place,time,longitude,latitude));
                Log.d("Manzana",magnitud + ";" + place+ ";" + longitude  + ";" + latitude);
                }
            } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(eqsData,"s");
        return  eqList;
    }
}
