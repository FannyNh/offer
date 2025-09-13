package com.thirdmoira.offer_backend.domain.models;

import lombok.Data;

@Data
public class User {
    Long userId;
    String firstName;
    String lastName;
    String email;
    Long groupId;
}
