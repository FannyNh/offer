package com.thirdmoira.offer_backend.data.mappers;

import com.thirdmoira.offer_backend.api.rest.ApiOffer;
import com.thirdmoira.offer_backend.domain.models.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ApiDomainOfferMapper {
    ApiOffer toApi(Offer offer);
}
