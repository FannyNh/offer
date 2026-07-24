package com.thirdmoira.offer_backend.api.rest;

import lombok.Data;

@Data
public class ApiServiceView {
    Long serviceId;
    Long categoryId;
    String categoryName;
    String title;
    String description;
}

