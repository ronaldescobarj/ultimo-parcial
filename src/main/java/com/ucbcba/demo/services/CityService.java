package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.City;

public interface CityService {

    Iterable<City> listAllCities();

    void saveCity(City city);

    City getCity(Integer id);

    void deleteCity(Integer id);

}
