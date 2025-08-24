package com.thirdmoira.offer_backend.data.mappers;

import com.thirdmoira.offer_backend.data.entities.GroupEntity;
import com.thirdmoira.offer_backend.data.entities.UserEntity;
import com.thirdmoira.offer_backend.domain.models.Group;
import com.thirdmoira.offer_backend.domain.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityDomainGroupMapper {
    Group toDomain(GroupEntity save);
    GroupEntity toEntity(Long groupId, String groupName);
}


