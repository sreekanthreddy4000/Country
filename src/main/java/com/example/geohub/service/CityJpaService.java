package com.example.geohub.service;

import com.example.geohub.model.City;
import com.example.geohub.model.Country;
import com.example.geohub.repository.CountryJpaRepository;
import com.example.geohub.repository.CityJpaRepository;
import com.example.geohub.repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityJpaService implements CityRepository {

    @Autowired
    private CityJpaRepository cityJpaRepository;

    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Override
    public ArrayList<City> getCities() {
        List<City> citylist = cityJpaRepository.findAll();
        ArrayList<City> cities = new ArrayList<>(citylist);
        return cities;
    }

    @Override
    public City getCityById(int cityId) {
        try {
            City city = cityJpaRepository.findById(cityId).get();
            return city;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public City addCity(City city) {

        Country country = city.getCountry();
        int countryId = country.getCountryId();

        try {
            country = countryJpaRepository.findById(countryId).get();
            city.setCountry(country);
            return cityJpaRepository.save(city);
        }

        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public City updateCity(int cityId, City city) {
        try {

            City newCity = cityJpaRepository.findById(cityId).get();

            if (city.getCountry() != null) {
                int countryId = city.getCountry().getCountryId();
                Country newCountry = countryJpaRepository.findById(countryId).get();
                newCity.setCountry(newCountry);
            }

            if (city.getCityName() != null) {
                newCity.setCityName(city.getCityName());
            }

            if (city.getPopulation() != 0) {
                newCity.setPopulation(city.getPopulation());
            }

            if (city.getLatitude() != null) {
                newCity.setLatitude(city.getLatitude());
            }

            if (city.getLongitude() != null) {
                newCity.setLongitude(city.getLongitude());
            }


            return cityJpaRepository.save(newCity);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCity(int cityId) {
        try {
            cityJpaRepository.deleteById(cityId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Country getCityCountry(int cityId) {
        try {
            City city = cityJpaRepository.findById(cityId).get();
            Country country = city.getCountry();
            return country;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}