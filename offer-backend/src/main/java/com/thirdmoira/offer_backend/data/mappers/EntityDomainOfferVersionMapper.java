package com.thirdmoira.offer_backend.data.mappers;

import com.thirdmoira.offer_backend.data.entities.OfferEntity;
import com.thirdmoira.offer_backend.data.entities.OfferVersionEntity;
import com.thirdmoira.offer_backend.data.entities.UserEntity;
import com.thirdmoira.offer_backend.domain.models.Offer;
import com.thirdmoira.offer_backend.domain.models.OfferVersion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityDomainOfferVersionMapper {
    @Mapping(target = "offer", expression = "java(mapOffer(offerId))")
    OfferVersionEntity toEntity(Long id, Long offerId, String title, Long versionNumber);

    @Mapping(source = "offer.id", target = "offerId")
    OfferVersion toDomain(OfferVersionEntity save);

    @SuppressWarnings("unused")
    default  OfferEntity mapOffer(Long offerId) {

        if (offerId == null) return null;
        OfferEntity offer = new OfferEntity();
        offer.setId(offerId);
        return offer;
    }


}
