package com.example.lul.emonitor;

import android.provider.BaseColumns;

/**
 * Created by Usre on 27/11/2017.
 */

public class EqContract {

    public class EqColumns implements BaseColumns{
        public static  final String TABLE_NAME = "earthquakes";
        public static final String MAGNITUDE = "magnitude";
        public static final String PLACE = "place";
        public static final String LONGITUDE = "longitude";
        public static final String LATITUDE = "latitude";
        public static final String TIME = "time";

        public static final int MAGNITUDE_INDEX = 1;
        public static final int PLACE_INDEX = 2;
        public static final int LONGITUDE_INDEX = 3;
        public static final int LATITUDE_INDEX = 4;
        public static final int TIME_INDEX = 5;
    }
}
