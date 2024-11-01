package com.denis.BdService.repository;

import com.denis.BdService.dto.FieldToDeleteBy;
import com.denis.BdService.dto.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deleteUsersByDynamicFields(List<FieldToDeleteBy> fields) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaDelete<UserEntity> delete = cb.createCriteriaDelete(UserEntity.class);

        Root<UserEntity> root = delete.from(UserEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        for (var field: fields) {
            predicates.add(cb.equal(root.get(field.field_name()), field.value()));

        }
        if(!predicates.isEmpty())
            delete.where(cb.and(predicates.toArray(new Predicate[0])));

        entityManager.createQuery(delete).executeUpdate();
    }
}
