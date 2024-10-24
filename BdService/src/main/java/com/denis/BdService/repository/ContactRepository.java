package com.denis.BdService.repository;

import com.denis.BdService.dto.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {
}
