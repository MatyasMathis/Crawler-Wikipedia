package com.example.crawlerwebdemo.dto;

import com.example.crawlerwebdemo.entity.City;
import com.example.crawlerwebdemo.entity.Country;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class CountryDto {

    private Long id;

    private String name;
    private Long population;
    private double area;

    private List<City> cities;


}
