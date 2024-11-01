package com.denis.BdService.repository;

import com.denis.BdService.dto.FieldToDeleteBy;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

public interface UserRepositoryCustom {
    void deleteUsersByDynamicFields(List<FieldToDeleteBy> fields);
}
