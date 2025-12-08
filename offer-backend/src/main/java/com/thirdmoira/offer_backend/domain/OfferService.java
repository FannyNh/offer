package com.thirdmoira.offer_backend.domain;

import com.thirdmoira.offer_backend.data.OfferRepository;
import com.thirdmoira.offer_backend.data.entities.OfferVersionEntity;
import com.thirdmoira.offer_backend.domain.models.Offer;
import com.thirdmoira.offer_backend.domain.models.OfferVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    @Transactional
    public Offer create(String name, Long id, String description, Long userId, String title, String status) {
       return offerRepository.create( name,  id,  description,  userId, title, "CREATED");
    }

    @Transactional
    public Offer update(String name, Long id, String description, Long userId, String title, String status) {
        return offerRepository.update( name,  id,  description,  userId, status);
    }



    public List<Offer> get() {
        return offerRepository.getAll();
    }

    public void delete(Long id) {
        offerRepository.delete(id);
    }

    public OfferVersion createVersion(  Long offerId, String title , Long versionNumber) {
        return offerRepository.createVersion(offerId,title);
    }
}
