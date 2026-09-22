package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiServiceCategoryList;
import com.thirdmoira.offer_backend.data.exceptions.UnauthorizedException;
import com.thirdmoira.offer_backend.data.mappers.ApiDomainServiceCategoryMapper;
import com.thirdmoira.offer_backend.domain.ServiceCategoryService;
import com.thirdmoira.offer_backend.domain.UserService;
import com.thirdmoira.offer_backend.domain.models.ServiceCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/service-categories")
@Slf4j
public class ServiceCategoryController {
    @Autowired
    private ServiceCategoryService serviceCategoryService;
    @Autowired
    private ApiDomainServiceCategoryMapper apiDomainServiceCategoryMapper;
    @Autowired
    private UserService userService;

    @GetMapping(produces = "application/json")
    public ApiServiceCategoryList getServiceCategories() {
        Long userId = getAuthenticatedUserId();
        List<ServiceCategory> categories = serviceCategoryService.getCategoriesForUser(userId);
        ApiServiceCategoryList response = new ApiServiceCategoryList();
        response.setCategories(categories.stream().map(apiDomainServiceCategoryMapper::toApi).toList());
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
