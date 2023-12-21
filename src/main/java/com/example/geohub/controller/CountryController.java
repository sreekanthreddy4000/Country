package com.example.geohub.controller;

import com.example.geohub.model.Country;
import com.example.geohub.service.CountryJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class CountryController {

    @Autowired
    private CountryJpaService countryService;

    @GetMapping("/countries")
    public ArrayList<Country> getCountries() {
        return countryService.getCountries();
    }

    @GetMapping("/countries/{countryId}")
    public Country getCountryById(@PathVariable("countryId") int countryId) {
        return countryService.getCountryById(countryId);
    }

    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @PutMapping("/countries/{countryId}")
    public Country updateCountry(@PathVariable("countryId") int countryId, @RequestBody Country country) {
        return countryService.updateCountry(countryId, country);
    }

    @DeleteMapping("countries/{countryId}")
    public void deleteCountry(@PathVariable int countryId) {
        countryService.deleteCountry(countryId);
    }
}
