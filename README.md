# Spring Boot

Spring Boot is an open source Java-based framework used to create a micro Service. 
Micro Service is an architecture that allows the developers to develop and deploy services independently.

# Kata_POIs

Kata_Pois is an spring boot project which calculate number of point of interest of an area and then display the most densest aareas.

## Requirements

For building and running the application you need:

- [JDK 1.11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.example.kata.pois.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Model
- Pois: entity with attributs: id, lat(Latitude) and lon(longitude) -> in which we load the content of CSV file
- Distance: entity with attributs: Pois1, Pois2, distance -> in witch we save the distance between points
- Area: entity with attributs: minLat, maxLat, minLon, maxLon -> in witch we save the most densest area

## Constant
Is a class in which i make all constants of the project for best organization (e.g the name of CSV file) 

## Service
 #### CSVReaderService: 
 - readCSV():  to read CSV file
 #### PoisService: 
 - densestAreas(): to return the densest area
 - displayDensestAreas():  to get the correct form required of densest area for the display
 - getInterval(): specific method which return the interval of gps coordinate (Pois)
 - calculDistance(): calculate the distance between two gps coordinate 
 - generateCombinationOf(): generate all possible combinations of Pois to can after that calculate the distance betweet each two (without duplicates)

## Endpoint
http://localhost:8080/api/pois
 Extract data from a CSV file then load it Pois Objects then display it json form.

http://localhost:8080/api/Pois/number/6.5/-7
 Calculate the number of Pois of an area with two parameters (minLat, minLon) (e.g minlat= 6.5 and minlon= -7)
 
http://localhost:8080/api/densest/areas/2
 find the 2 most densest areas 



## What's inside

The project is a working application with a some setup. It contains:
 - [Junit](https://junit.org/junit5/docs/current/user-guide/#running-tests-build-maven)
 - [Lombok](https://projectlombok.org/setup/maven)
 - [Opencsv](http://opencsv.sourceforge.net/#how_can_i_use_it_in_my_maven_projects)
 - [Decimal4j](https://mvnrepository.com/artifact/org.decimal4j/decimal4j/1.0.3)
