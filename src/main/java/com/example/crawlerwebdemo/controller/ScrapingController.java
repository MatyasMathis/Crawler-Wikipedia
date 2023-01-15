package com.example.crawlerwebdemo.controller;

import com.example.crawlerwebdemo.dto.CityDto;
import com.example.crawlerwebdemo.dto.CountryDto;
import com.example.crawlerwebdemo.service.CityService;
import com.example.crawlerwebdemo.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class ScrapingController {
    private CountryService countryService;
    private CityService cityService;

    public ScrapingController(CountryService countryService, CityService cityService) {
        this.countryService = countryService;
        this.cityService = cityService;
    }

    @GetMapping("/")
    public String getAllCountriesAndCities(Model model)  {
        List<CountryDto> countries=countryService.getAll();
        List<CityDto> cities=cityService.getAll();
        model.addAttribute("countries",countries);
        model.addAttribute("cities",cities);

        return "scraping";
    }

    @GetMapping("/scraping")
    public String addCountriesAndCities() throws IOException {
        countryService.countryScraping();
        cityService.cityScraping();

        return "redirect:/";
    }
}
