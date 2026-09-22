package com.thirdmoira.offer_backend.api.rest;

import lombok.Data;

@Data
public class ApiServiceCategoryView {
    Long serviceId;
    Long categoryId;
    String categoryName;
}
