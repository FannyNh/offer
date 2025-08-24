package com.thirdmoira.offer_backend.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique=true)
    private String email;
    @Column(name = "password_hash")
    private String password;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OfferEntity> offers;


}
