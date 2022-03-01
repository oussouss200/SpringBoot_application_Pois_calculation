package com.example.kata.pois.model;


import java.util.Objects;

public class Distance implements Comparable {

    private Pois pois1;

    private Pois pois2;

    private double distance;

    public Distance(Pois pois1, Pois pois2, double distance) {
        this.pois1 = pois1;
        this.pois2 = pois2;
        this.distance = distance;
    }

    public Pois getPois1() {
        return pois1;
    }

    public void setPois1(Pois pois1) {
        this.pois1 = pois1;
    }

    public Pois getPois2() {
        return pois2;
    }

    public void setPois2(Pois pois2) {
        this.pois2 = pois2;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    @Override
    public int compareTo(Object o) {
        Distance compareToEmp = (Distance) o;

        return Double.compare(distance, compareToEmp.distance);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance1 = (Distance) o;
        return Double.compare(distance1.distance, distance) == 0 && Objects.equals(pois1, distance1.pois1) && Objects.equals(pois2, distance1.pois2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pois1, pois2, distance);
    }
}
