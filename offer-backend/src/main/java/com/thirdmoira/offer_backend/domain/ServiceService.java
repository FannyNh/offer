package com.thirdmoira.offer_backend.domain;

import com.thirdmoira.offer_backend.data.ServiceCategoryRepository;
import com.thirdmoira.offer_backend.data.ServiceRepository;
import com.thirdmoira.offer_backend.data.entities.ServiceCategoryEntity;
import com.thirdmoira.offer_backend.data.entities.ServiceEntity;
import com.thirdmoira.offer_backend.data.exceptions.ServiceCategoryNotFoundException;
import com.thirdmoira.offer_backend.data.exceptions.ServiceNotFoundException;
import com.thirdmoira.offer_backend.domain.models.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    public Service updateCategory(Long userId, Long serviceId, Long categoryId) {
        ServiceEntity serviceEntity = serviceRepository.findEntityByIdAndUserId(serviceId, userId)
                .orElseThrow(() -> new ServiceNotFoundException("Service not found"));

        if (categoryId == null) {
            serviceEntity.setCategory(null);
        } else {
            ServiceCategoryEntity categoryEntity = serviceCategoryRepository
                    .findEntityByIdAndUserId(categoryId, userId)
                    .orElseThrow(() -> new ServiceCategoryNotFoundException("Service category not found"));
            serviceEntity.setCategory(categoryEntity);
        }

        return serviceRepository.save(serviceEntity);
    }

    public List<Service> getServicesForOffer(Long offerId) {
        return serviceRepository.findServicesByOfferId(offerId);
    }
}
