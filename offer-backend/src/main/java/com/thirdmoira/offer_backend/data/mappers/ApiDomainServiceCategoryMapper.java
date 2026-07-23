package com.thirdmoira.offer_backend.data.mappers;

import com.thirdmoira.offer_backend.api.rest.ApiServiceCategory;
import com.thirdmoira.offer_backend.domain.models.ServiceCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApiDomainServiceCategoryMapper {
    ApiServiceCategory toApi(ServiceCategory category);
}
