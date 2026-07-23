package com.thirdmoira.offer_backend.api.rest;

import lombok.Data;

import java.util.List;

@Data
public class ApiOfferEditView {
    Long userId;
    Long id;
    ApiOfferVersionLight version;
    List<ApiServiceEditView> services;
}
