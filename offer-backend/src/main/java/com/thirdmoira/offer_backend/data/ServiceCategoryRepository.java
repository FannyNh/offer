package com.thirdmoira.offer_backend.data;

import com.thirdmoira.offer_backend.data.entities.ServiceCategoryEntity;
import com.thirdmoira.offer_backend.data.jpa.ServiceCategoryJpaRepository;
import com.thirdmoira.offer_backend.data.mappers.EntityDomainServiceCategoryMapper;
import com.thirdmoira.offer_backend.domain.models.ServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServiceCategoryRepository {
    @Autowired
    private ServiceCategoryJpaRepository jpaRepository;
    @Autowired
    private EntityDomainServiceCategoryMapper entityDomainServiceCategoryMapper;

    public List<ServiceCategory> getByUserId(Long userId) {
        return jpaRepository.findAllByUserUserId(userId)
                .stream()
                .map(entityDomainServiceCategoryMapper::toDomain)
                .toList();
    }

    public Optional<ServiceCategoryEntity> findEntityByIdAndUserId(Long id, Long userId) {
        return jpaRepository.findByIdAndUserUserId(id, userId);
    }
}
