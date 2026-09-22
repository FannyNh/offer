package com.thirdmoira.offer_backend.api.rest;

import lombok.Data;

@Data
    public  class ApiOfferVersionLight {
        String name;
        String title;
        String description;
        Long versionNumber;
    }