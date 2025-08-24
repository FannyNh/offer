package com.thirdmoira.offer_backend.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long groupId;
    @Column(name = "group_name", unique=true)
    private String groupName;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<UserEntity> users;

}
