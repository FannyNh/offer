package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiCreateOrUpdateServiceRequest;
import com.thirdmoira.offer_backend.api.rest.ApiServiceCategoryUpdateRequest;
import com.thirdmoira.offer_backend.api.rest.ApiServiceCategoryView;
import com.thirdmoira.offer_backend.api.rest.ApiServiceView;
import com.thirdmoira.offer_backend.data.exceptions.UnauthorizedException;
import com.thirdmoira.offer_backend.domain.ServiceService;
import com.thirdmoira.offer_backend.domain.UserService;
import com.thirdmoira.offer_backend.domain.models.Service;
import com.thirdmoira.offer_backend.domain.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceControllerTest {

    @InjectMocks
    private ServiceController controller;

    @Mock
    private ServiceService serviceService;

    @Mock
    private UserService userService;

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void should_create_service_for_authenticated_user() {
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken("uid-123", null, "ROLE_USER")
        );

        User user = new User();
        user.setUserId(7L);
        when(userService.getUserByUid("uid-123")).thenReturn(user);

        Service created = new Service();
        created.setId(10L);
        created.setTitle("Design Sprint");
        created.setDescription("5-day workshop");
        created.setCategoryId(2L);
        created.setCategoryName("Design");
        when(serviceService.create(7L, 2L, "Design Sprint", "5-day workshop")).thenReturn(created);

        ApiCreateOrUpdateServiceRequest request = new ApiCreateOrUpdateServiceRequest();
        request.setCategoryId(2L);
        request.setTitle("Design Sprint");
        request.setDescription("5-day workshop");

        ApiServiceView response = controller.createService(request);

        assertEquals(10L, response.getServiceId());
        assertEquals("Design Sprint", response.getTitle());
        assertEquals("5-day workshop", response.getDescription());
        assertEquals(2L, response.getCategoryId());
        assertEquals("Design", response.getCategoryName());
    }

    @Test
    void should_update_service_for_authenticated_user() {
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken("uid-123", null, "ROLE_USER")
        );

        User user = new User();
        user.setUserId(7L);
        when(userService.getUserByUid("uid-123")).thenReturn(user);

        Service updated = new Service();
        updated.setId(11L);
        updated.setTitle("Architecture Review");
        updated.setDescription("System audit");
        updated.setCategoryId(3L);
        updated.setCategoryName("Consulting");
        when(serviceService.update(7L, 11L, 3L, "Architecture Review", "System audit")).thenReturn(updated);

        ApiCreateOrUpdateServiceRequest request = new ApiCreateOrUpdateServiceRequest();
        request.setCategoryId(3L);
        request.setTitle("Architecture Review");
        request.setDescription("System audit");

        ApiServiceView response = controller.updateService(11L, request);

        assertEquals(11L, response.getServiceId());
        assertEquals("Architecture Review", response.getTitle());
        assertEquals("System audit", response.getDescription());
        assertEquals(3L, response.getCategoryId());
        assertEquals("Consulting", response.getCategoryName());
    }

    @Test
    void should_delete_service_for_authenticated_user() {
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken("uid-123", null, "ROLE_USER")
        );

        User user = new User();
        user.setUserId(7L);
        when(userService.getUserByUid("uid-123")).thenReturn(user);

        controller.deleteService(55L);

        verify(serviceService).delete(7L, 55L);
    }

    @Test
    void should_update_service_category_for_authenticated_user() {
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken("uid-123", null, "ROLE_USER")
        );

        User user = new User();
        user.setUserId(7L);
        when(userService.getUserByUid("uid-123")).thenReturn(user);

        Service updated = new Service();
        updated.setId(12L);
        updated.setCategoryId(4L);
        updated.setCategoryName("Operations");
        when(serviceService.updateCategory(7L, 12L, 4L)).thenReturn(updated);

        ApiServiceCategoryUpdateRequest request = new ApiServiceCategoryUpdateRequest();
        request.setCategoryId(4L);

        ApiServiceCategoryView response = controller.updateServiceCategory(12L, request);

        assertEquals(12L, response.getServiceId());
        assertEquals(4L, response.getCategoryId());
        assertEquals("Operations", response.getCategoryName());
    }

    @Test
    void should_throw_unauthorized_when_authentication_is_missing() {
        SecurityContextHolder.clearContext();

        ApiCreateOrUpdateServiceRequest request = new ApiCreateOrUpdateServiceRequest();
        request.setTitle("Design Sprint");

        UnauthorizedException ex = assertThrows(
                UnauthorizedException.class,
                () -> controller.createService(request)
        );

        assertEquals("Authentication required", ex.getMessage());
    }
}

