package com.thirdmoira.offer_backend.api;


import com.thirdmoira.offer_backend.api.rest.ApiCreateOrUpdateGroupRequest;
import com.thirdmoira.offer_backend.api.rest.ApiGroup;
import com.thirdmoira.offer_backend.api.rest.ApiUser;
import com.thirdmoira.offer_backend.data.mappers.ApiDomainGroupMapper;
import com.thirdmoira.offer_backend.domain.GroupService;
import com.thirdmoira.offer_backend.domain.models.Group;
import com.thirdmoira.offer_backend.domain.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Annotation pour dire que cette classe est un contrôleur REST
@RestController
@RequestMapping("/api/groups")
@Slf4j
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private ApiDomainGroupMapper apiDomainGroupMapper;



    @GetMapping(produces = "application/json")
    public List<ApiGroup> getGroups() {
        log.info("get test");
        List<Group> groups = groupService.get();

        return groups.stream()
                .map(apiDomainGroupMapper::toApi)
                .toList();
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ApiGroup createOrUpdateGroup(@RequestBody ApiCreateOrUpdateGroupRequest request) {
        log.info("createOrUpdateGroup test");
        Group group = groupService.createOrUpdateGroup(
                request.getGroupId(),
                request.getGroupName());
        return apiDomainGroupMapper.toApi(group);
    }

    @DeleteMapping("/{id}")

    public void deleteGroup(@PathVariable Long id) {
        log.info("deleteGroup test");
        groupService.delete(id);
    }

}
