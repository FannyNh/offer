package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiCreateOrUpdateOfferRequest;
import com.thirdmoira.offer_backend.api.rest.ApiOffer;
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

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ApiOffer createOrUpdateOffer(@RequestBody ApiCreateOrUpdateOfferRequest request) {
        log.info("Received request to create or update offer");

        Offer newOffer = offerService.createOrUpdate(
                request.getName(),
                request.getId(),
                request.getDescription(),
                request.getUserId(),
                request.getTitle()
        );

        return apiDomainOfferMapper.toApi(newOffer);
    }

    @GetMapping(produces = "application/json")
    public List<ApiOffer> getOffers() {
        log.info("get list offers");
        List<Offer> offers = offerService.get();
        return offers.stream().map(apiDomainOfferMapper::toApi).toList();
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id) {
        offerService.delete(id);
    }
}