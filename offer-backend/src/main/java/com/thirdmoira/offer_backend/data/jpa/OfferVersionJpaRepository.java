package com.thirdmoira.offer_backend.data.jpa;

import com.thirdmoira.offer_backend.data.entities.OfferEntity;
import com.thirdmoira.offer_backend.data.entities.OfferVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferVersionJpaRepository extends JpaRepository<OfferVersionEntity, Long> {

}
