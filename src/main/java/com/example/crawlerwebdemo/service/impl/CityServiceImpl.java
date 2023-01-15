package com.example.crawlerwebdemo.service.impl;

import com.example.crawlerwebdemo.dto.CityDto;
import com.example.crawlerwebdemo.dto.CountryDto;
import com.example.crawlerwebdemo.entity.City;
import com.example.crawlerwebdemo.entity.Country;
import com.example.crawlerwebdemo.mapper.CityMapper;
import com.example.crawlerwebdemo.mapper.CountryMapper;
import com.example.crawlerwebdemo.repository.CityRepository;
import com.example.crawlerwebdemo.repository.CountryRepository;
import com.example.crawlerwebdemo.service.CityService;
import com.example.crawlerwebdemo.util.ChangeFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;
    private CountryRepository countryRepository;

    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CityDto> getAll() {
        List<City> cities = cityRepository.findAll();
        return cities.stream().map(CityMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void save(CityDto cityDto) {
        City city = CityMapper.mapToCity(cityDto);
        cityRepository.save(city);
    }

    @Override
    public boolean isInit(CityDto cityDto) {
        City city = CityMapper.mapToCity(cityDto);
        if (cityRepository.findByName(cityDto.getName()) == null) {
            return false;
        }
        return true;
    }

    public void cityScraping() throws IOException {
        String urlCountries = "https://en.wikipedia.org/wiki/List_of_largest_cities";
        Document doc = Jsoup.connect(urlCountries).get();
        Element table = doc.select("table.static-row-numbers, table.plainrowheaders ,table.vertical-align-top ,table.sortable ,table.wikitable ,table.jquery-tablesorter").last();
        Elements body = table.getElementsByTag("tbody");

        int i = 0;
        for (Element e0 : body.select("tr")) {
            if (i > 2) {
                CityDto cityDto = new CityDto();


                cityDto.setName(e0.child(0).select("a").text().toString());

                cityDto.setPopulation(ChangeFormat.textToInt(e0.child(2).text().toString()));
                String popText = ChangeFormat.clearString(e0.child(6).text().toString());
                cityDto.setDensity(ChangeFormat.textToDouble(popText));
                String countryName = e0.child(1).text().toString();
                if(countryName.equals("DR Congo")){
                    Country country = (Country) countryRepository.findByName("Democratic Republic of the Congo");
                    cityDto.setCountry(country);
                }else{
                    Country country = (Country) countryRepository.findByName(countryName);
                    cityDto.setCountry(country);
                }


                if (!isInit(cityDto)) {
                    save(cityDto);
                }
                //save(cityDto);



            }
            i++;


        }


    }
}