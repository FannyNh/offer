package com.thirdmoira.offer_backend.domain;

import com.thirdmoira.offer_backend.data.GroupRepository;
import com.thirdmoira.offer_backend.domain.models.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    public List<Group> get() {
        return groupRepository.get();
    }


    public Group createOrUpdateGroup(Long groupId, String groupName) {
        return groupRepository.createOrUpdateGroup(groupId,groupName);
    }

    public void delete(Long id) {
        groupRepository.delete(id);
    }
}
