package com.thirdmoira.offer_backend.data.mappers;

import com.thirdmoira.offer_backend.data.entities.ServiceEntity;
import com.thirdmoira.offer_backend.domain.models.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityDomainServiceMapper {
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    Service toDomain(ServiceEntity entity);
}
