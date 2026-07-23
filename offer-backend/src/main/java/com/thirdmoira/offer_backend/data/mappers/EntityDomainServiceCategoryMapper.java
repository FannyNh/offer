package com.thirdmoira.offer_backend.data.mappers;

import com.thirdmoira.offer_backend.data.entities.ServiceCategoryEntity;
import com.thirdmoira.offer_backend.data.entities.UserEntity;
import com.thirdmoira.offer_backend.domain.models.ServiceCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityDomainServiceCategoryMapper {

    @Mapping(source = "user.userId", target = "userId")
    ServiceCategory toDomain(ServiceCategoryEntity entity);

    @Mapping(target = "user", expression = "java(mapUser(userId))")
    ServiceCategoryEntity toEntity(Long id, Long userId, String name);

    @SuppressWarnings("unused")
    default UserEntity mapUser(Long userId) {
        if (userId == null) return null;
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        return user;
    }
}
