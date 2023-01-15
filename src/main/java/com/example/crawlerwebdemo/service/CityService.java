package com.example.crawlerwebdemo.service;

import com.example.crawlerwebdemo.dto.CityDto;

import java.io.IOException;
import java.util.List;

public interface CityService {
    List<CityDto> getAll();
    void save(CityDto cityDto);

    boolean isInit(CityDto cityDto);

    void cityScraping() throws IOException;
}
