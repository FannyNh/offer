package com.thirdmoira.offer_backend.data;

import com.thirdmoira.offer_backend.data.entities.OfferEntity;
import com.thirdmoira.offer_backend.data.entities.OfferVersionEntity;
import com.thirdmoira.offer_backend.data.exceptions.OfferNotFoundException;
import com.thirdmoira.offer_backend.data.jpa.OfferJpaRepository;
import com.thirdmoira.offer_backend.data.jpa.OfferVersionJpaRepository;
import com.thirdmoira.offer_backend.data.mappers.EntityDomainOfferMapper;
import com.thirdmoira.offer_backend.data.mappers.EntityDomainOfferVersionMapper;
import com.thirdmoira.offer_backend.domain.models.Offer;
import com.thirdmoira.offer_backend.domain.models.OfferVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfferRepository {
    @Autowired
    private OfferJpaRepository jpaRepository;
    @Autowired
    private EntityDomainOfferMapper entityDomainOfferMapper;
    @Autowired
    private EntityDomainOfferVersionMapper entityDomainOfferVersionMapper;
    @Autowired
    private OfferVersionJpaRepository jpaVersionRepository;

    public Offer createOrUpdate(String name, Long id, String description, Long userId,String status) {

        OfferEntity offer = entityDomainOfferMapper.toEntity( id, userId,status );

        OfferEntity save = jpaRepository.save(offer);
        return entityDomainOfferMapper.toDomain(save);
    }

    public List<Offer> getAll() {
        List<OfferEntity> offers = jpaRepository.findAll();
        return offers.stream().map(entityDomainOfferMapper::toDomain).toList();
    }

    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    public Offer update(String name, Long id, String description, Long userId,String status) {
        OfferEntity newOffer = entityDomainOfferMapper.toEntity( id, userId,status);
        OfferEntity save = jpaRepository.save(newOffer);
        OfferVersion newVersion = createVersion(save.getId(), name);
        return entityDomainOfferMapper.toDomain(save);
    }

    public Offer create(String name, Long id, String description, Long userId, String title,String status) {
        OfferEntity newOffer = entityDomainOfferMapper.toEntity( id, userId,status);
        OfferEntity save = jpaRepository.save(newOffer);
        if (save.getId() != null) {
            initOfferVersion(save.getId(), title);
        }
        return entityDomainOfferMapper.toDomain(save);
    }

    private void initOfferVersion(Long offerId, String title) {
        OfferVersionEntity offerVersionNew = entityDomainOfferVersionMapper.toEntity(null, offerId, title, 1L);
        jpaVersionRepository.save(offerVersionNew);
    }

    public OfferVersion createVersion(Long offerId, String title) {
        OfferVersionEntity lastVersion = getLastVersion(offerId);
        Long newLastVersionNumber = lastVersion.getVersionNumber() + 1;
        OfferVersionEntity offerVersionNew = entityDomainOfferVersionMapper.toEntity(null, offerId, title, newLastVersionNumber);
        OfferVersionEntity save = jpaVersionRepository.save(offerVersionNew);
        return entityDomainOfferVersionMapper.toDomain(save);
    }


    private OfferVersionEntity getLastVersion(Long offerId) {
        return jpaVersionRepository.findTopByOfferIdOrderByVersionNumberDesc(offerId);
    }

    private OfferEntity getById(Long offerId) {
        return jpaRepository.findById(offerId).orElseThrow(
                () -> new OfferNotFoundException("offer not found")
        );
    }
}
