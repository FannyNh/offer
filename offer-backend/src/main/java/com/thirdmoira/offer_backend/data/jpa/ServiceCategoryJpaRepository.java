package com.thirdmoira.offer_backend.data.jpa;

import com.thirdmoira.offer_backend.data.entities.ServiceCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceCategoryJpaRepository extends JpaRepository<ServiceCategoryEntity, Long> {
    List<ServiceCategoryEntity> findAllByUserUserId(Long userId);
    Optional<ServiceCategoryEntity> findByIdAndUserUserId(Long id, Long userId);
}
