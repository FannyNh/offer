package com.thirdmoira.offer_backend.data.jpa;

import com.thirdmoira.offer_backend.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    // Tu peux ajouter des méthodes personnalisées ici si besoin

    /**
     *
     * @param email
     * @return
     */
    Optional<UserEntity> findByEmail(String email);
}
