package com.thirdmoira.offer_backend.domain.models;

import lombok.Data;

@Data
public class Offer {
    String name;
    String description;
    Long userId;
    Long id;
}
