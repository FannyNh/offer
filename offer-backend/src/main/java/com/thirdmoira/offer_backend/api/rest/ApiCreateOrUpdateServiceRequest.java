package com.thirdmoira.offer_backend.api.rest;

import lombok.Data;

@Data
public class ApiCreateOrUpdateServiceRequest {
    Long categoryId;
    String title;
    String description;
}

