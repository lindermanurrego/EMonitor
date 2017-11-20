package com.example.lul.emonitor;

/**
 * Created by User on 10/11/2017.
 */

public class Earthquake {
    private String magnitude;
    private String place;


    public Earthquake(String magnitude, String place) {
        this.magnitude = magnitude;
        this.place = place;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
