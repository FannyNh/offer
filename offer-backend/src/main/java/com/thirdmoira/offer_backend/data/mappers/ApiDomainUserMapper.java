package com.thirdmoira.offer_backend.data.mappers;

import com.thirdmoira.offer_backend.api.rest.ApiUser;
import com.thirdmoira.offer_backend.domain.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApiDomainUserMapper {
    ApiUser toApi(User user);
}


