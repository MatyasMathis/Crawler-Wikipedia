package com.example.crawlerwebdemo.repository;

import com.example.crawlerwebdemo.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
    City findByName(String name);
}
