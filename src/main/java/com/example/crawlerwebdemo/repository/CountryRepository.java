package com.example.crawlerwebdemo.repository;

import com.example.crawlerwebdemo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
    Country findByName(String name);
}
