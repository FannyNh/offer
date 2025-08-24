package com.thirdmoira.offer_backend.data.mappers;

import com.thirdmoira.offer_backend.data.entities.UserEntity;
import com.thirdmoira.offer_backend.domain.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityDomainUserMapper {
    User toDomain(UserEntity save);
    UserEntity toEntity(String firstName, String lastName, String email, String password, Long groupId, Long userId);
}


