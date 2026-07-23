package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiServiceCategory;
import com.thirdmoira.offer_backend.api.rest.ApiServiceCategoryList;
import com.thirdmoira.offer_backend.data.mappers.ApiDomainServiceCategoryMapper;
import com.thirdmoira.offer_backend.domain.ServiceCategoryService;
import com.thirdmoira.offer_backend.domain.UserService;
import com.thirdmoira.offer_backend.domain.models.ServiceCategory;
import com.thirdmoira.offer_backend.domain.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceCategoryControllerTest {

    @InjectMocks
    private ServiceCategoryController controller;

    @Mock
    private ServiceCategoryService serviceCategoryService;

    @Mock
    private ApiDomainServiceCategoryMapper apiDomainServiceCategoryMapper;

    @Mock
    private UserService userService;

    @Test
    void should_return_service_categories_for_authenticated_user() {
        // given
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken("uid-123", null, "ROLE_USER")
        );

        User user = new User();
        user.setUserId(7L);
        when(userService.getUserByUid("uid-123")).thenReturn(user);

        ServiceCategory category = new ServiceCategory();
        category.setId(1L);
        category.setName("Design");

        ApiServiceCategory apiCategory = new ApiServiceCategory();
        apiCategory.setId(1L);
        apiCategory.setName("Design");

        when(serviceCategoryService.getCategoriesForUser(7L)).thenReturn(List.of(category));
        when(apiDomainServiceCategoryMapper.toApi(category)).thenReturn(apiCategory);

        // when
        ApiServiceCategoryList result = controller.getServiceCategories();

        // then
        assertNotNull(result);
        assertEquals(1, result.getCategories().size());
        assertEquals("Design", result.getCategories().get(0).getName());
    }

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }
}
