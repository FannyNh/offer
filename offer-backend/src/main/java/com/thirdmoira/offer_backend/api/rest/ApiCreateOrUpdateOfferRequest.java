package com.thirdmoira.offer_backend.api.rest;

import lombok.Data;

@Data
public class ApiCreateOrUpdateOfferRequest {
    String name;
    String description;
    Long userId;
    Long id;
    String title;
}
