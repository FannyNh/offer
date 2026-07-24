package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiServiceCategory;
import com.thirdmoira.offer_backend.api.rest.ApiServiceCategoryList;
import com.thirdmoira.offer_backend.data.exceptions.UnauthorizedException;
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

    @Test
    void should_return_empty_list_when_user_has_no_categories() {
        // given
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken("uid-456", null, "ROLE_USER")
        );

        User user = new User();
        user.setUserId(42L);
        when(userService.getUserByUid("uid-456")).thenReturn(user);
        when(serviceCategoryService.getCategoriesForUser(42L)).thenReturn(List.of());

        // when
        ApiServiceCategoryList result = controller.getServiceCategories();

        // then
        assertNotNull(result);
        assertNotNull(result.getCategories());
        assertTrue(result.getCategories().isEmpty());
        verify(apiDomainServiceCategoryMapper, never()).toApi(any());
    }

    @Test
    void should_throw_unauthorized_when_authentication_is_missing() {
        // given
        SecurityContextHolder.clearContext();

        // when / then
        UnauthorizedException ex = assertThrows(
                UnauthorizedException.class,
                () -> controller.getServiceCategories()
        );
        assertEquals("Authentication required", ex.getMessage());
    }

    @Test
    void should_throw_unauthorized_when_principal_is_not_uid_string() {
        // given
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken(123L, null, "ROLE_USER")
        );

        // when / then
        UnauthorizedException ex = assertThrows(
                UnauthorizedException.class,
                () -> controller.getServiceCategories()
        );
        assertEquals("Authentication required", ex.getMessage());
        verifyNoInteractions(userService);
    }

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }
}
