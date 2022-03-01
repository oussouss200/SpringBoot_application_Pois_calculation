package com.example.kata.pois.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;

import java.util.Objects;


@AllArgsConstructor
public class Pois {

    @CsvBindByName(column = "id")
    private int id;

    @CsvBindByName(column = "lat")
    private double lat;

    @CsvBindByName(column = "lon")
    private double lon;

    public Pois() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pois pois = (Pois) o;
        return id == pois.id && Double.compare(pois.lat, lat) == 0 && Double.compare(pois.lon, lon) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lat, lon);
    }
}
