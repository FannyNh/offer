package com.thirdmoira.offer_backend.api.rest;

import lombok.Data;

@Data
public class ApiCreateOrUpdateUserRequest {
    String firstName;
    String lastName;
    String email;
    String password;
    Long groupId;
    Long userId;

}
