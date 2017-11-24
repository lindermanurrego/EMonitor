package com.example.lul.emonitor;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 10/11/2017.
 */

public class Earthquake implements Parcelable {
    private Double magnitude;
    private String place;


    public Earthquake(Double magnitude, String place) {
        this.magnitude = magnitude;
        this.place = place;
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
    }

    protected Earthquake(Parcel in) {
        this.magnitude = (Double) in.readValue(Double.class.getClassLoader());
        this.place = in.readString();
    }

    public static final Parcelable.Creator<Earthquake> CREATOR = new Parcelable.Creator<Earthquake>() {
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
