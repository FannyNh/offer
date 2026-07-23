package com.thirdmoira.offer_backend.domain;

import com.thirdmoira.offer_backend.data.ServiceCategoryRepository;
import com.thirdmoira.offer_backend.domain.models.ServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategoryService {
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    public List<ServiceCategory> getCategoriesForUser(Long userId) {
        return serviceCategoryRepository.getByUserId(userId);
    }
}
