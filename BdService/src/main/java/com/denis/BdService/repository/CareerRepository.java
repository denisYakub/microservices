package com.denis.BdService.repository;

import com.denis.BdService.dto.CareerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CareerRepository extends JpaRepository<CareerEntity, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM CareerEntity c WHERE c.user.id IN :users_id")
    void deleteByUserId(@Param("users_id") List<Integer> users_id);
}
