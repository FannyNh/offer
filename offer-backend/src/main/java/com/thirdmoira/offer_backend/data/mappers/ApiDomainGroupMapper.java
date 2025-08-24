package com.thirdmoira.offer_backend.data.mappers;


import com.thirdmoira.offer_backend.api.rest.ApiGroup;
import com.thirdmoira.offer_backend.domain.models.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApiDomainGroupMapper {
    ApiGroup toApi(Group group);
}
