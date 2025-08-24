package com.thirdmoira.offer_backend.data;


import com.thirdmoira.offer_backend.data.entities.GroupEntity;
import com.thirdmoira.offer_backend.data.exceptions.NameAlreadyExistException;
import com.thirdmoira.offer_backend.data.jpa.GroupJpaRepository;
import com.thirdmoira.offer_backend.data.mappers.EntityDomainGroupMapper;
import com.thirdmoira.offer_backend.domain.models.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GroupRepository {
    @Autowired
    GroupJpaRepository jpaRepository;
    @Autowired
    EntityDomainGroupMapper entityDomainGroupMapper;

    public List<Group> get() {
        List<GroupEntity> groups = jpaRepository.findAll();
        return groups.stream()
                .map(entityDomainGroupMapper::toDomain)
                .toList();
    }

    public Group createOrUpdateGroup(Long groupId, String name) {
        GroupEntity group = entityDomainGroupMapper.toEntity(groupId,name);
        if(group.getGroupId() == null) {
            checkGroupNameExist(group);
        }
        GroupEntity save = jpaRepository.save(group);
        return entityDomainGroupMapper.toDomain(save);
    }

    private void checkGroupNameExist(GroupEntity group) {
        Optional<GroupEntity> byName = jpaRepository.findByGroupName(group.getGroupName());
        if(byName.isPresent()) {
            throw new NameAlreadyExistException("Name already exist" + group.getGroupName());
        }
    }

    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
