package com.thirdmoira.offer_backend.api.rest;

import lombok.Data;

@Data
public class ApiServiceEditView {
    Long serviceId;
    String title;
    String description;
    Long categoryId;
    String categoryName;
}
