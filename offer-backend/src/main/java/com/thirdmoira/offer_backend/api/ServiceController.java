package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiCreateOrUpdateServiceRequest;
import com.thirdmoira.offer_backend.api.rest.ApiServiceCategoryUpdateRequest;
import com.thirdmoira.offer_backend.api.rest.ApiServiceCategoryView;
import com.thirdmoira.offer_backend.api.rest.ApiServiceView;
import com.thirdmoira.offer_backend.data.exceptions.UnauthorizedException;
import com.thirdmoira.offer_backend.domain.ServiceService;
import com.thirdmoira.offer_backend.domain.UserService;
import com.thirdmoira.offer_backend.domain.models.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/services")
@Slf4j
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ApiServiceView createService(@RequestBody ApiCreateOrUpdateServiceRequest request) {
        Long userId = getAuthenticatedUserId();
        Service service = serviceService.create(
                userId,
                request.getCategoryId(),
                request.getTitle(),
                request.getDescription()
        );
        return toApiServiceView(service);
    }

    @PutMapping(value = "/{serviceId}", consumes = "application/json", produces = "application/json")
    public ApiServiceView updateService(@PathVariable Long serviceId,
                                        @RequestBody ApiCreateOrUpdateServiceRequest request) {
        Long userId = getAuthenticatedUserId();
        Service service = serviceService.update(
                userId,
                serviceId,
                request.getCategoryId(),
                request.getTitle(),
                request.getDescription()
        );
        return toApiServiceView(service);
    }

    @DeleteMapping(value = "/{serviceId}")
    public void deleteService(@PathVariable Long serviceId) {
        Long userId = getAuthenticatedUserId();
        serviceService.delete(userId, serviceId);
    }

    @PatchMapping(value = "/{serviceId}/category", consumes = "application/json", produces = "application/json")
    public ApiServiceCategoryView updateServiceCategory(@PathVariable Long serviceId,
                                                        @RequestBody ApiServiceCategoryUpdateRequest request) {
        Long userId = getAuthenticatedUserId();
        Service service = serviceService.updateCategory(userId, serviceId, request.getCategoryId());
        return toApiServiceCategoryView(service);
    }

    private ApiServiceView toApiServiceView(Service service) {
        ApiServiceView response = new ApiServiceView();
        response.setServiceId(service.getId());
        response.setCategoryId(service.getCategoryId());
        response.setCategoryName(service.getCategoryName());
        response.setTitle(service.getTitle());
        response.setDescription(service.getDescription());
        return response;
    }

    private ApiServiceCategoryView toApiServiceCategoryView(Service service) {
        ApiServiceCategoryView response = new ApiServiceCategoryView();
        response.setServiceId(service.getId());
        response.setCategoryId(service.getCategoryId());
        response.setCategoryName(service.getCategoryName());
        return response;
    }

    private Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("Authentication required");
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof String)) {
            throw new UnauthorizedException("Authentication required");
        }
        String uid = (String) principal;
        return userService.getUserByUid(uid).getUserId();
    }
}
