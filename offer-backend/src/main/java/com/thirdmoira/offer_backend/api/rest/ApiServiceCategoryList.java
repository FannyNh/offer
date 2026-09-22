package com.thirdmoira.offer_backend.api.rest;

import lombok.Data;

import java.util.List;

@Data
public class ApiServiceCategoryList {
    List<ApiServiceCategory> categories;
}
