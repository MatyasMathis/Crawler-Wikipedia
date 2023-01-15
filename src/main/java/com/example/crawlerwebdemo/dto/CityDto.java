package com.example.crawlerwebdemo.dto;

import com.example.crawlerwebdemo.entity.Country;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class CityDto {

    private Long id;

    private String name;

    private Long population;
    private double density;

    private Country country;


}

