package com.denis.BdService.repository;

import com.denis.BdService.dto.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository extends JpaRepository<PersonalEntity, Integer> {
}
