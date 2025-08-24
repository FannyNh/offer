package com.thirdmoira.offer_backend.data.mappers;

import com.thirdmoira.offer_backend.data.entities.OfferEntity;
import com.thirdmoira.offer_backend.data.entities.UserEntity;
import com.thirdmoira.offer_backend.domain.models.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityDomainOfferMapper {
    @Mapping(target = "user", expression = "java(mapUser(userId))")
    OfferEntity toEntity(String name, Long id, String description, Long userId);

    @Mapping(source = "save.user.userId", target = "userId")
    Offer toDomain(OfferEntity save);

    @SuppressWarnings("unused")
    default UserEntity mapUser(Long userId) {

        if (userId == null) return null;
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        return user;
    }


}
