package com.example.lul.emonitor;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by User on 18/11/2017.
 */

public class DownloadEqAsyncTask extends AsyncTask<URL,Void,String> {
    String eqData = "";
    public DownloadEqsInterface delegate;

    public interface DownloadEqsInterface{
        void onEqsDownloaded(String eqsData);
    }

    @Override
    protected String doInBackground(URL...urls) {
        try{
            eqData = downloadData(urls[0]);
        }catch (IOException e){
            e.printStackTrace();
        }
        return eqData;
    }

    @Override
    protected void onPostExecute(String eqData) {

        super.onPostExecute(eqData);
        delegate.onEqsDownloaded(eqData);
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
/*            Toast.makeText(this,"",Toast.LENGTH_LONG);
            Toast.makeText(this, "Hubo error al descargar los datos de terremotos", Toast.LENGTH_SHORT).show();
*/
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

}
