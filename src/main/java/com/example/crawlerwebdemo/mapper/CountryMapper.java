package com.example.crawlerwebdemo.mapper;

import com.example.crawlerwebdemo.dto.CountryDto;
import com.example.crawlerwebdemo.entity.Country;

public class CountryMapper {
    public static CountryDto mapToDto(Country country){
        CountryDto countryDto=CountryDto.builder()
                .id(country.getId())
                .cities(country.getCities())
                .area(country.getArea())
                .population(country.getPopulation())
                .name(country.getName())
        .build();
        return countryDto;
    }


    public static Country mapToCountry(CountryDto countryDto){
        Country country=Country.builder()
                .id(countryDto.getId())
                .cities(countryDto.getCities())
                .area(countryDto.getArea())
                .population(countryDto.getPopulation())
                .name(countryDto.getName())
                .build();
        return country;
    }
}
