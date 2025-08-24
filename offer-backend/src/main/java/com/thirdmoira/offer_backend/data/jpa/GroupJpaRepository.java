package com.thirdmoira.offer_backend.data.jpa;

import com.thirdmoira.offer_backend.data.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GroupJpaRepository extends JpaRepository<GroupEntity, Long> {
    Optional<GroupEntity> findByGroupName(String groupName);
}
