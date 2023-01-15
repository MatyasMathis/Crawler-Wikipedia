package com.example.crawlerwebdemo.mapper;

import com.example.crawlerwebdemo.dto.CityDto;
import com.example.crawlerwebdemo.entity.City;

public class CityMapper {
    public static CityDto mapToDto(City city){
        CityDto cityDto=CityDto.builder()
                .country(city.getCountry())
                .id(city.getId())
                .name(city.getName())
                .density(city.getDensity())
                .population(city.getPopulation())
                .build();
        return cityDto;
    }

    public static City mapToCity(CityDto cityDto){
        City city=City.builder()
                .country(cityDto.getCountry())
                .id(cityDto.getId())
                .name(cityDto.getName())
                .density(cityDto.getDensity())
                .population(cityDto.getPopulation())
                .build();
        return city;
    }
}
