package com.denis.BdService.repository;

import com.denis.BdService.dto.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<EducationEntity, Integer> {
}
