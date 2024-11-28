package com.denis.BdService.repository;

import com.denis.BdService.dto.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>, UserRepositoryCustom{
    @Modifying
    @Transactional
    @Query("DELETE FROM UserEntity u WHERE u.id IN :users_id")
    void deleteByUserId(@Param("users_id") List<Integer> users_id);

    @Modifying
    @Transactional
    @Query("SELECT u FROM UserEntity u WHERE u.id BETWEEN :startId AND :endId")
    List<UserEntity> findUsersByIdRange(@Param("startId") int startId, @Param("endId") int endId);
}
