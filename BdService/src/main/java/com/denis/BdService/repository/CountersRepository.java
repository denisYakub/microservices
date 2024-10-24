package com.denis.BdService.repository;

import com.denis.BdService.dto.CounterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountersRepository extends JpaRepository<CounterEntity, Integer> {
}
