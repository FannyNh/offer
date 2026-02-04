package com.thirdmoira.offer_backend.api.rest;

import lombok.Data;

@Data
public class ApiOfferLight {
    Long userId;
    Long id;
    ApiOfferVersionLight version;
}

