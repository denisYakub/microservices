package com.denis.BdService.repository;

import com.denis.BdService.dto.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
    CountryEntity findByTitle(String title);
}
