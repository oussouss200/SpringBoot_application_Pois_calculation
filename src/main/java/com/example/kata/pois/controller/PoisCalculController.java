package com.example.kata.pois.controller;

import com.example.kata.pois.model.Distance;
import com.example.kata.pois.model.Area;
import com.example.kata.pois.model.Pois;
import com.example.kata.pois.service.CSVReaderService;
import com.example.kata.pois.service.impl.PoisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class PoisCalculController {

    private static final Logger log = LoggerFactory.getLogger(PoisCalculController.class);

    @Autowired
    private final CSVReaderService csvReaderService;

    @Autowired
    private final PoisServiceImpl poisServiceImpl;

    public PoisCalculController(CSVReaderService csvReaderService, PoisServiceImpl poisServiceImpl) {
        this.csvReaderService = csvReaderService;
        this.poisServiceImpl = poisServiceImpl;
    }

    /**
     * @return return list of all Pois from the csv file
     */
    @GetMapping(value = {"/pois"}, produces = "application/json")
    public ResponseEntity<List<Pois>> getAllPois() {
        log.info("getAllPois()");
        List<Pois> poisList = csvReaderService.readCSV();
        return new ResponseEntity<>(poisList, HttpStatus.OK);
    }

    /**
     * @param minlat minimum latitude of area in question
     * @param minlon minimum longitude of area in question
     * @return return number of Pois in this area
     */
    @GetMapping(value = {"Pois/number/{minlat}/{minlon}"}, produces = "application/json")
    public long getPoisNumber(@PathVariable float minlat, @PathVariable float minlon) {
        log.info("getPoisNumber()");
        List<Pois> poisList = csvReaderService.readCSV();
        return poisList.stream().filter(pois -> pois.getLat() > minlat && pois.getLon() > minlon).count();
    }

    /**
     * @param nombreZone how many densest zone should we return
     * @return return the n list of most densest areas
     */
    @GetMapping(value = {"densest/areas/{nombreZone}"}, produces = "application/json")
    public ResponseEntity<List<Area>> getdensestAreas(@PathVariable int nombreZone) {
        log.info("getDensestAreas()");
        List<Pois> poisList = csvReaderService.readCSV();
        List<Distance> densestAreas = poisServiceImpl.densestAreas(poisList, 2);
        List<Area> displayAreaList = poisServiceImpl.displayDensestAreas(densestAreas);
        return new ResponseEntity<>(displayAreaList, HttpStatus.OK);
    }
}
