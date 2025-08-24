package com.thirdmoira.offer_backend.api.rest;

import lombok.Data;

@Data
public class ApiCreateOrUpdateGroupRequest {
    String groupName;
    Long groupId;

}
