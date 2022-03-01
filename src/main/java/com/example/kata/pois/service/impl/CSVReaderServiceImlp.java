package com.example.kata.pois.service.impl;

import com.example.kata.pois.constant.Constants;
import com.example.kata.pois.model.Pois;
import com.example.kata.pois.service.CSVReaderService;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVReaderServiceImlp implements CSVReaderService {

    public CSVReaderServiceImlp() {
    }

    /**
     * @return list of pois extracted from the csv file
     */
    @Override
    public List<Pois> readCSV() {
        List<Pois> poisList = new ArrayList<>();
        try {
            poisList = new CsvToBeanBuilder(new FileReader(Constants.filePath))
                    .withType(Pois.class)
                    .build()
                    .parse();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return poisList;
    }

}
