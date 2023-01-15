package com.example.crawlerwebdemo.service.impl;

import com.example.crawlerwebdemo.dto.CountryDto;
import com.example.crawlerwebdemo.entity.Country;
import com.example.crawlerwebdemo.mapper.CountryMapper;
import com.example.crawlerwebdemo.repository.CountryRepository;
import com.example.crawlerwebdemo.service.CountryService;
import com.example.crawlerwebdemo.util.ChangeFormat;
import org.apache.catalina.mapper.Mapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryDto> getAll() {
        List<Country> countries=countryRepository.findAll();
        return countries.stream().map(CountryMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void save(CountryDto countryDto) {
        Country country= CountryMapper.mapToCountry(countryDto);
        countryRepository.save(country);
    }

    @Override
    public boolean isInit(CountryDto countryDto) {
        Country country=CountryMapper.mapToCountry(countryDto);
        if (countryRepository.findByName(countryDto.getName())==null){
            return false;
        }
        return true;
    }

    @Override
    public void countryScraping() throws IOException {
        String urlCountries="https://en.wikipedia.org/wiki/List_of_countries_by_population_in_2010";
        Document doc= Jsoup.connect(urlCountries).get();
        Element table = doc.select("table.sortable.wikitable").first();
        Elements rows = table.select("tr:has(td)");


        for(Element row: rows){



            CountryDto countryDto=new CountryDto();

            int i=0;
            for(Element cell:row.children()){

                if(i==1 && !cell.select("a").text().isEmpty()){
                    countryDto.setName(cell.select("a").text());

                }
                if(i==2 && !cell.text().toString().isEmpty()){
                    long population= ChangeFormat.textToInt(cell.text().toString());
                    countryDto.setPopulation(population);
                }
                if(i==4 && !cell.text().toString().isEmpty()){
                    double area= ChangeFormat.textToDouble(cell.text().toString());
                    countryDto.setArea(area);
                }

                i++;
            }
           if(!isInit(countryDto)){
               save(countryDto);
           }





        }
        
    }
}
