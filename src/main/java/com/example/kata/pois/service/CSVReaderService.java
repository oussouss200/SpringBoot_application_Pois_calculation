package com.example.kata.pois.service;

import com.example.kata.pois.model.Pois;

import java.util.List;

public interface CSVReaderService {

    List<Pois> readCSV();
}
