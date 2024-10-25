package com.denis.BdService.repository;

import com.denis.BdService.dto.OccupationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupationRepository extends JpaRepository<OccupationEntity, Integer> {
}
