package com.thirdmoira.offer_backend.domain;

import com.thirdmoira.offer_backend.data.ServiceCategoryRepository;
import com.thirdmoira.offer_backend.data.ServiceRepository;
import com.thirdmoira.offer_backend.data.entities.ServiceCategoryEntity;
import com.thirdmoira.offer_backend.data.entities.ServiceEntity;
import com.thirdmoira.offer_backend.data.entities.UserEntity;
import com.thirdmoira.offer_backend.data.exceptions.ServiceCategoryNotFoundException;
import com.thirdmoira.offer_backend.data.exceptions.ServiceNotFoundException;
import com.thirdmoira.offer_backend.domain.models.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    public Service create(Long userId, Long categoryId, String title, String description) {
        ServiceEntity entity = new ServiceEntity();
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        entity.setUser(user);
        entity.setCategory(resolveCategoryEntity(userId, categoryId));
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setBasePrice(BigDecimal.ZERO);
        entity.setActive(true);
        return serviceRepository.save(entity);
    }

    public Service update(Long userId, Long serviceId, Long categoryId, String title, String description) {
        ServiceEntity entity = serviceRepository.findEntityByIdAndUserId(serviceId, userId)
                .orElseThrow(() -> new ServiceNotFoundException("Service not found"));

        entity.setCategory(resolveCategoryEntity(userId, categoryId));
        entity.setTitle(title);
        entity.setDescription(description);
        return serviceRepository.save(entity);
    }

    public void delete(Long userId, Long serviceId) {
        ServiceEntity entity = serviceRepository.findEntityByIdAndUserId(serviceId, userId)
                .orElseThrow(() -> new ServiceNotFoundException("Service not found"));
        serviceRepository.delete(entity);
    }

    public Service updateCategory(Long userId, Long serviceId, Long categoryId) {
        ServiceEntity serviceEntity = serviceRepository.findEntityByIdAndUserId(serviceId, userId)
                .orElseThrow(() -> new ServiceNotFoundException("Service not found"));

        serviceEntity.setCategory(resolveCategoryEntity(userId, categoryId));

        return serviceRepository.save(serviceEntity);
    }

    public List<Service> getServicesForOffer(Long offerId) {
        return serviceRepository.findServicesByOfferId(offerId);
    }

    private ServiceCategoryEntity resolveCategoryEntity(Long userId, Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return serviceCategoryRepository.findEntityByIdAndUserId(categoryId, userId)
                .orElseThrow(() -> new ServiceCategoryNotFoundException("Service category not found"));
    }
}
