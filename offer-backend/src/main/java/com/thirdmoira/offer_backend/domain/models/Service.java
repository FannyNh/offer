package com.thirdmoira.offer_backend.domain.models;

import lombok.Data;

@Data
public class Service {
    Long id;
    Long userId;
    Long categoryId;
    String categoryName;
    String title;
    String description;
}
