package com.example.kata.pois.service.impl;

import com.example.kata.pois.model.Distance;
import com.example.kata.pois.model.Area;
import com.example.kata.pois.model.Pois;
import com.example.kata.pois.service.PoisService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PoisServiceImpl implements PoisService {

    public PoisServiceImpl() {
    }

    /**
     * @param poisList   pois list that we will calcul the densest areas
     * @param zoneNumber numbre of areas to return
     * @return the n densest areas from the poislist
     */
    @Override
    public List<Distance> densestAreas(List<Pois> poisList, int zoneNumber) {

        // call the method generateCombinationOf() to get all possible combinations of Pois (without duplicate)
        Set<Set<Pois>> result = generateCombinationOf(poisList);
        List<Distance> distanceList = new ArrayList<>();

        // we calculate distance between pois then generate object Distance for each two
        result.stream().map(Set::toArray)
                .forEach(tuple -> distanceList.add(new Distance((Pois) tuple[0], (Pois) tuple[1], calculDistance((Pois) tuple[0], (Pois) tuple[1]))));

        // sort ascending the list of distances using comparator
        Collections.sort(distanceList, new Comparator<Distance>() {
            @Override
            public int compare(Distance o1, Distance o2) {
                return o1.compareTo(o2);
            }
        });
        // return the first n Distances
        return distanceList.stream().limit(zoneNumber).collect(Collectors.toList());
    }

    /**
     * @param distanceList list of distance
     * @return return list of areas genrated from distance list
     */
    @Override
    public List<Area> displayDensestAreas(List<Distance> distanceList) {
        List<Area> areaList = new ArrayList<>();

        // for each distance we call getInterval() with lat and lon on parameters to get to limit them in an interval
        distanceList.forEach(distance -> {
            areaList.add(new Area(getInterval(distance.getPois1().getLat(), distance.getPois2().getLat()),
                    getInterval((distance.getPois1().getLon()), distance.getPois2().getLon())));
        });
        return areaList;
    }

    /**
     * @param a lat of first Pois
     * @param b lat of second Pois
     * @return return the two limits of the interval with nearest 0.5
     */
    public double[] getInterval(double a, double b) {
        double[] interval = new double[2];
        double min = Math.min(a, b);
        double max = Math.max(a, b);
        if (String.valueOf(min - (int) min).equals("0.5") || String.valueOf(min - (int) min).equals("-0.5"))
            interval[0] = min;
        else if (min - (int) min < 0.5 * Math.signum(min))
            interval[0] = Math.round(min);
        else interval[0] = (int) min + 0.5;
        interval[1] = interval[0] + 0.5;
        while (max > interval[1])
            interval[1] += (0.5 * Math.signum(interval[1]));
        return interval;
    }

    /**
     * @param pois1 Point D'intérêt numéro 1
     * @param pois2 Point D'intérêt numéro 2
     * @return distance between pois1 and pois 2
     */
    private double calculDistance(Pois pois1, Pois pois2) {

        // Radius of the earth
        final int R = 6371;

        double latDistance = Math.toRadians(pois2.getLat() - pois1.getLat());
        double lonDistance = Math.toRadians(pois2.getLon() - pois1.getLon());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(pois1.getLat())) * Math.cos(Math.toRadians(pois2.getLat()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    /**
     * @param poisLists list of pois that we will calcul possible combinations
     * @return return set of possible combinations
     */
    public Set<Set<Pois>> generateCombinationOf(List<Pois> poisLists) {
        Set<Set<Pois>> result = new HashSet<>();
        combinations(poisLists, new ArrayList<>(), result, 2, 0);
        return result;
    }


    public void combinations(List<Pois> values, List<Pois> current, Set<Set<Pois>> accumulator, int size, int pos) {
        if (current.size() == size) {
            Set<Pois> toAdd = new HashSet<>(current);
            if (accumulator.contains(toAdd)) {
                throw new RuntimeException("Duplicated value " + current);
            }
            accumulator.add(toAdd);
            return;
        }
        for (int i = pos; i <= values.size() - size + current.size(); i++) {
            current.add(values.get(i));
            combinations(values, current, accumulator, size, i + 1);
            current.remove(current.size() - 1);
        }
    }

}
