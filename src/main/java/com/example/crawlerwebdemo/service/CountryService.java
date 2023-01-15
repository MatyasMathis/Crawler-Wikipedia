package com.example.crawlerwebdemo.service;

import com.example.crawlerwebdemo.dto.CountryDto;
import com.example.crawlerwebdemo.entity.Country;

import java.io.IOException;
import java.util.List;

public interface CountryService {
    List<CountryDto> getAll();
    void save(CountryDto countryDto);
    boolean isInit(CountryDto countryDto);

    void countryScraping() throws IOException;
}
