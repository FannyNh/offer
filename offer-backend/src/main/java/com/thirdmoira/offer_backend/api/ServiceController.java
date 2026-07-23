package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiServiceCategoryUpdateRequest;
import com.thirdmoira.offer_backend.api.rest.ApiServiceCategoryView;
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

    @PatchMapping(value = "/{serviceId}/category", consumes = "application/json", produces = "application/json")
    public ApiServiceCategoryView updateServiceCategory(@PathVariable Long serviceId,
                                                        @RequestBody ApiServiceCategoryUpdateRequest request) {
        Long userId = getAuthenticatedUserId();
        Service service = serviceService.updateCategory(userId, serviceId, request.getCategoryId());
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
