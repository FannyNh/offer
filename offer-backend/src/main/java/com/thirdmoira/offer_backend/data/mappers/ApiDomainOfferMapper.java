package com.thirdmoira.offer_backend.data.mappers;

import com.thirdmoira.offer_backend.api.rest.ApiOfferLight;
import com.thirdmoira.offer_backend.domain.models.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ApiDomainOfferMapper {
    ApiOfferLight toApi(Offer offer);
}
