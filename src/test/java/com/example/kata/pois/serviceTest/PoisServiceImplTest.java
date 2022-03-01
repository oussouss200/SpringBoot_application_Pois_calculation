package com.example.kata.pois.serviceTest;

import com.example.kata.pois.model.Distance;
import com.example.kata.pois.model.Pois;
import com.example.kata.pois.service.impl.PoisServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

public class PoisServiceImplTest {

    private PoisServiceImpl sut;

    @BeforeEach
    public void setUp() {
        this.sut = new PoisServiceImpl();
    }

    @Test
    public void densestAreasTest() {
        // ARRANGE
        Pois pois1 = new Pois(1, -48.6, -37.7);
        Pois pois2 = new Pois(2, -27.1, 8.4);
        Pois pois3 = new Pois(3, 6.6, -6.9);
        Pois pois4 = new Pois(4, -2.3, 38.3);
        Pois pois5 = new Pois(5, 6.8, -6.9);
        Pois pois6 = new Pois(6, -2.5, 38.3);
        Pois pois7 = new Pois(7, 0.1, -0.1);
        Pois pois8 = new Pois(8, -2.1, 38.1);
        List<Pois> poisList = Arrays.asList(pois1, pois2, pois3, pois4, pois5, pois6, pois7, pois8);

        // ACT
        List<Distance> result = sut.densestAreas(poisList, 2);

        // ASSERT
        List<Distance> expected = Arrays.asList(new Distance(pois4, pois6, 22238.985328911767), new Distance(pois3, pois5, 22238.985328911767));

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void generateCombinationOfTest() {
        // ARRANGE
        Pois pois1 = new Pois(1, -48.6, -37.7);
        Pois pois2 = new Pois(2, -27.1, 8.4);
        Pois pois3 = new Pois(3, 6.6, -6.9);
        Pois pois4 = new Pois(4, -2.3, 38.3);
        Pois pois5 = new Pois(5, 6.8, -6.9);
        Pois pois6 = new Pois(6, -2.5, 38.3);
        Pois pois7 = new Pois(7, 0.1, -0.1);
        Pois pois8 = new Pois(8, -2.1, 38.1);
        List<Pois> poisList = Arrays.asList(pois1, pois2, pois3, pois4, pois5, pois6, pois7, pois8);

        // ACT
        Set<Set<Pois>> result = sut.generateCombinationOf(poisList);

        // ASSERT

        // List of possible combination
        Set<Pois> set1 = new HashSet<>(Arrays.asList(pois1, pois2));
        Set<Pois> set2 = new HashSet<>(Arrays.asList(pois1, pois3));
        Set<Pois> set3 = new HashSet<>(Arrays.asList(pois1, pois4));
        Set<Pois> set4 = new HashSet<>(Arrays.asList(pois1, pois5));
        Set<Pois> set5 = new HashSet<>(Arrays.asList(pois1, pois6));
        Set<Pois> set6 = new HashSet<>(Arrays.asList(pois1, pois7));
        Set<Pois> set7 = new HashSet<>(Arrays.asList(pois1, pois8));
        Set<Pois> set8 = new HashSet<>(Arrays.asList(pois2, pois3));
        Set<Pois> set9 = new HashSet<>(Arrays.asList(pois2, pois4));
        Set<Pois> set10 = new HashSet<>(Arrays.asList(pois2, pois5));
        Set<Pois> set11 = new HashSet<>(Arrays.asList(pois2, pois6));
        Set<Pois> set12 = new HashSet<>(Arrays.asList(pois2, pois7));
        Set<Pois> set13 = new HashSet<>(Arrays.asList(pois2, pois8));
        Set<Pois> set14 = new HashSet<>(Arrays.asList(pois3, pois4));
        Set<Pois> set15 = new HashSet<>(Arrays.asList(pois3, pois5));
        Set<Pois> set16 = new HashSet<>(Arrays.asList(pois3, pois6));
        Set<Pois> set17 = new HashSet<>(Arrays.asList(pois3, pois7));
        Set<Pois> set18 = new HashSet<>(Arrays.asList(pois3, pois8));
        Set<Pois> set19 = new HashSet<>(Arrays.asList(pois4, pois5));
        Set<Pois> set20 = new HashSet<>(Arrays.asList(pois4, pois6));
        Set<Pois> set21 = new HashSet<>(Arrays.asList(pois4, pois7));
        Set<Pois> set22 = new HashSet<>(Arrays.asList(pois4, pois8));
        Set<Pois> set23 = new HashSet<>(Arrays.asList(pois5, pois6));
        Set<Pois> set24 = new HashSet<>(Arrays.asList(pois5, pois7));
        Set<Pois> set25 = new HashSet<>(Arrays.asList(pois5, pois8));
        Set<Pois> set26 = new HashSet<>(Arrays.asList(pois6, pois7));
        Set<Pois> set27 = new HashSet<>(Arrays.asList(pois6, pois8));
        Set<Pois> set28 = new HashSet<>(Arrays.asList(pois7, pois8));

        Set<Set<Pois>> expected = new HashSet<Set<Pois>>(Arrays.asList(set1, set2, set3, set4, set5, set6,
                set7, set8, set9, set10, set11, set12, set13, set14, set15, set16, set17, set18, set19,
                set20, set21, set22, set23, set24, set25, set26, set27, set28));

        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"-2.3, -2.5, -2.5, -2", "38.3, 38.3, 38, 38.5", "6.6, 6.8, 6.5, 7", "-6.9, -6.9, -7, -6.5"})
    public void getIntervalTest(double a, double b, double expected1, double expected2) {

        // ACT
        double[] result = sut.getInterval(a, b);

        // ASSERT
        Assertions.assertEquals(result[0], expected1);
        Assertions.assertEquals(result[1], expected2);
    }
}
