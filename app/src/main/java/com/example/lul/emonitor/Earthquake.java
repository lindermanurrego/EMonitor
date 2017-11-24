package com.example.lul.emonitor;

/**
 * Created by User on 10/11/2017.
 */

public class Earthquake {
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
}
