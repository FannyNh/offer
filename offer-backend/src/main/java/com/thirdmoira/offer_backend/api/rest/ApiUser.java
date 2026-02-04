package com.thirdmoira.offer_backend.api.rest;

import lombok.Data;

@Data
public class ApiUser {
    Long userId;
    String firstName;
    String lastName;
    String email;
    Long groupId;
}
