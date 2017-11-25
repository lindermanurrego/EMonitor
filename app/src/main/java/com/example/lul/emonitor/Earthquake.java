package com.example.lul.emonitor;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 10/11/2017.
 */

public class Earthquake implements Parcelable {
    private Double magnitude;
    private String place;
    private Long time;
    private String longitude;
    private String latitude;


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Earthquake(Double magnitude, String place, Long time, String longitude, String latitude) {
        this.magnitude = magnitude;
        this.place = place;
        this.time = time;
        this.latitude = latitude;

        this.longitude = longitude;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.magnitude);
        dest.writeString(this.place);
        dest.writeValue(this.time);
        dest.writeString(this.longitude);
        dest.writeString(this.latitude);
    }

    protected Earthquake(Parcel in) {
        this.magnitude = (Double) in.readValue(Double.class.getClassLoader());
        this.place = in.readString();
        this.time = (Long) in.readValue(Long.class.getClassLoader());
        this.longitude = in.readString();
        this.latitude = in.readString();
    }

    public static final Creator<Earthquake> CREATOR = new Creator<Earthquake>() {
        @Override
        public Earthquake createFromParcel(Parcel source) {
            return new Earthquake(source);
        }

        @Override
        public Earthquake[] newArray(int size) {
            return new Earthquake[size];
        }
    };
}
