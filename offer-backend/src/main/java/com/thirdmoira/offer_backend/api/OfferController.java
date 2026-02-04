package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiCreateOfferRequest;
import com.thirdmoira.offer_backend.api.rest.ApiOfferLight;
import com.thirdmoira.offer_backend.data.mappers.ApiDomainOfferMapper;
import com.thirdmoira.offer_backend.domain.OfferService;
import com.thirdmoira.offer_backend.domain.models.Offer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@Slf4j
public class OfferController {
    @Autowired
    private ApiDomainOfferMapper apiDomainOfferMapper;

    @Autowired
    private OfferService offerService;

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
    public ApiOfferLight getOfferById(@PathVariable Long id) {
        Offer offer = offerService.getById(id);
        return apiDomainOfferMapper.toApi(offer);
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id) {
        offerService.delete(id);
    }
}