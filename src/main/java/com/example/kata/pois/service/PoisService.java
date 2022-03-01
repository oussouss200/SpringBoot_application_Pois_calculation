package com.example.kata.pois.service;

import com.example.kata.pois.model.Distance;
import com.example.kata.pois.model.Area;
import com.example.kata.pois.model.Pois;

import java.util.List;

public interface PoisService {

    List<Distance> densestAreas(List<Pois> poisList, int zoneNumber);

    List<Area> displayDensestAreas(List<Distance> distanceList);
}
