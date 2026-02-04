package com.thirdmoira.offer_backend.data.mappers;

import com.thirdmoira.offer_backend.data.entities.OfferEntity;
import com.thirdmoira.offer_backend.data.entities.OfferVersionEntity;
import com.thirdmoira.offer_backend.data.entities.UserEntity;
import com.thirdmoira.offer_backend.domain.models.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityDomainOfferMapper {
    @Mapping(target = "user", expression = "java(mapUser(userId))")
    OfferEntity toEntity(Long id, Long userId, String status);

    @Mapping(source = "offerEntity.user.userId", target = "userId")
    Offer toDomain(OfferEntity offerEntity);

    @Mapping(source = "offerEntity.id", target = "id")
    @Mapping(source = "offerEntity.user.userId", target = "userId")
    Offer toDomainWithVersion(OfferEntity offerEntity, OfferVersionEntity version );

    @SuppressWarnings("unused")
    default UserEntity mapUser(Long userId) {

        if (userId == null) return null;
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        return user;
    }


}
