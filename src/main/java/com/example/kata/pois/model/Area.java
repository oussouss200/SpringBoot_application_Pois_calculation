package com.example.kata.pois.model;

public class Area {

    private double minLat;
    private double maxLat;
    private double minLon;
    private double maxlon;

    public Area(double minLat, double maxLat, double minLon, double maxLon) {
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.maxlon = maxLon;
    }

    public Area(double[] lat, double[] lon) {
        this.minLat = lat[0];
        this.maxLat = lat[1];
        this.minLon = lon[0];
        this.maxlon = lon[1];
    }

    public double getMinLat() {
        return minLat;
    }

    public void setMinLat(double minLat) {
        this.minLat = minLat;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(double maxLat) {
        this.maxLat = maxLat;
    }

    public double getMinLon() {
        return minLon;
    }

    public void setMinLon(double minLon) {
        this.minLon = minLon;
    }

    public double getMaxLon() {
        return maxlon;
    }

    public void setMaxLon(double maxLon) {
        this.maxlon = maxLon;
    }
}
