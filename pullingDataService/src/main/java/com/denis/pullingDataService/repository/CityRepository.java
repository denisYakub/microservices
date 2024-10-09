package com.denis.pullingDataService.repository;

import com.denis.pullingDataService.dto.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {
    CityEntity findByTitle(String title);
}
