package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiCreateOfferRequest;
import com.thirdmoira.offer_backend.api.rest.ApiOfferEditView;
import com.thirdmoira.offer_backend.api.rest.ApiOfferLight;
import com.thirdmoira.offer_backend.api.rest.ApiServiceEditView;
import com.thirdmoira.offer_backend.data.mappers.ApiDomainOfferMapper;
import com.thirdmoira.offer_backend.domain.OfferService;
import com.thirdmoira.offer_backend.domain.models.Offer;
import com.thirdmoira.offer_backend.domain.models.OfferEditView;
import com.thirdmoira.offer_backend.domain.models.Service;
import com.thirdmoira.offer_backend.domain.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@Slf4j
public class OfferController {
    @Autowired
    private ApiDomainOfferMapper apiDomainOfferMapper;

    @Autowired
    private OfferService offerService;
    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ApiOfferLight createOffer(@RequestBody ApiCreateOfferRequest request) {
        log.info("Received request to create or update offer");
        Offer newOffer = offerService.create(
                request.getName(),
                request.getId(),
                request.getDescription(),
                request.getUserId(),
                request.getTitle(),
                request.getStatus()
        );

        return apiDomainOfferMapper.toApi(newOffer);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ApiOfferLight updateOffer(@RequestBody ApiCreateOfferRequest request) {
        log.info("Received request to create or update offer");

        Offer newOffer = offerService.update(
                request.getName(),
                request.getId(),
                request.getDescription(),
                request.getUserId(),
                request.getTitle(),
                request.getStatus()
        );

        return apiDomainOfferMapper.toApi(newOffer);
    }

    @GetMapping(produces = "application/json")
    public List<ApiOfferLight> getOffers() {
        List<Offer> offerWithLastVersion = offerService.get();
        return offerWithLastVersion.stream().map(apiDomainOfferMapper::toApi).toList();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ApiOfferEditView getOfferById(@PathVariable Long id) {
        Offer offer = offerService.getById(id);
        ApiOfferLight apiOffer = apiDomainOfferMapper.toApi(offer);
        ApiOfferEditView response = new ApiOfferEditView();
        response.setId(apiOffer.getId());
        response.setUserId(apiOffer.getUserId());
        response.setVersion(apiOffer.getVersion());

        Long userId = getAuthenticatedUserIdOrNull();
        if (userId != null && userId.equals(apiOffer.getUserId())) {
            OfferEditView editView = offerService.getEditView(id, userId);
            response.setServices(editView.getServices().stream().map(this::toApiService).toList());
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id) {
        offerService.delete(id);
    }

    private Long getAuthenticatedUserIdOrNull() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof String)) {
            return null;
        }
        String uid = (String) principal;
        return userService.getUserByUid(uid).getUserId();
    }

    private ApiServiceEditView toApiService(Service service) {
        ApiServiceEditView api = new ApiServiceEditView();
        api.setServiceId(service.getId());
        api.setTitle(service.getTitle());
        api.setDescription(service.getDescription());
        api.setCategoryId(service.getCategoryId());
        api.setCategoryName(service.getCategoryName());
        return api;
    }
}
