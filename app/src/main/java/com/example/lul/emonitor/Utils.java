package com.example.lul.emonitor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Usre on 27/11/2017.
 */

public class Utils {

    public static boolean  isNetworwAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return  networkInfo != null && networkInfo.isConnected();


    }
}
