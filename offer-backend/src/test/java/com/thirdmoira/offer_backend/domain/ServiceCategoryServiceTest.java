package com.thirdmoira.offer_backend.domain;

import com.thirdmoira.offer_backend.data.ServiceCategoryRepository;
import com.thirdmoira.offer_backend.domain.models.ServiceCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceCategoryServiceTest {

    @InjectMocks
    private ServiceCategoryService serviceCategoryService;

    @Mock
    private ServiceCategoryRepository serviceCategoryRepository;

    @Test
    void should_return_categories_for_user() {
        ServiceCategory category = new ServiceCategory();
        category.setId(3L);
        category.setName("Consulting");

        when(serviceCategoryRepository.getByUserId(9L)).thenReturn(List.of(category));

        List<ServiceCategory> result = serviceCategoryService.getCategoriesForUser(9L);

        assertEquals(1, result.size());
        assertEquals("Consulting", result.get(0).getName());
    }
}
