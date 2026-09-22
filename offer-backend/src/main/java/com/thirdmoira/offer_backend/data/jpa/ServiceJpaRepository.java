package com.thirdmoira.offer_backend.data.jpa;

import com.thirdmoira.offer_backend.data.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, Long> {
    Optional<ServiceEntity> findByIdAndUserUserId(Long id, Long userId);

    @Query("select distinct s from ServiceEntity s " +
            "join OfferItemEntity oi on oi.service = s " +
            "join OfferSectionEntity os on oi.section = os " +
            "join OfferVersionEntity ov on os.offerVersion = ov " +
            "join OfferEntity o on ov.offer = o " +
            "where o.id = :offerId")
    List<ServiceEntity> findServicesByOfferId(@Param("offerId") Long offerId);
}
