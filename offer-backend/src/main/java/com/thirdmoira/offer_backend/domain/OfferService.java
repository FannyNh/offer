package com.thirdmoira.offer_backend.domain;

import com.thirdmoira.offer_backend.data.OfferRepository;
import com.thirdmoira.offer_backend.domain.models.Offer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;
    public Offer createOrUpdate(String name, Long id, String description, Long userId, String title) {
                if(id != null) {
                    return offerRepository.update( name,  id,  description,  userId);
                }
                return offerRepository.create( name,  id,  description,  userId, title);
    }

    public List<Offer> get() {
        return  offerRepository.get();
    }



    public void delete(Long id) {
        offerRepository.delete(id);
    }
}
