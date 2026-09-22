package com.thirdmoira.offer_backend.data;

import com.thirdmoira.offer_backend.data.entities.ServiceEntity;
import com.thirdmoira.offer_backend.data.jpa.ServiceJpaRepository;
import com.thirdmoira.offer_backend.data.mappers.EntityDomainServiceMapper;
import com.thirdmoira.offer_backend.domain.models.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServiceRepository {
    @Autowired
    private ServiceJpaRepository jpaRepository;
    @Autowired
    private EntityDomainServiceMapper entityDomainServiceMapper;

    public Optional<ServiceEntity> findEntityByIdAndUserId(Long id, Long userId) {
        return jpaRepository.findByIdAndUserUserId(id, userId);
    }

    public Service save(ServiceEntity entity) {
        return entityDomainServiceMapper.toDomain(jpaRepository.save(entity));
    }

    public List<Service> findServicesByOfferId(Long offerId) {
        return jpaRepository.findServicesByOfferId(offerId)
                .stream()
                .map(entityDomainServiceMapper::toDomain)
                .toList();
    }

    public void delete(ServiceEntity entity) {
        jpaRepository.delete(entity);
    }
}
