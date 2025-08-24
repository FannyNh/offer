package com.thirdmoira.offer_backend.data;

import com.thirdmoira.offer_backend.data.entities.OfferEntity;
import com.thirdmoira.offer_backend.data.entities.OfferVersionEntity;
import com.thirdmoira.offer_backend.data.jpa.OfferJpaRepository;
import com.thirdmoira.offer_backend.data.jpa.OfferVersionJpaRepository;
import com.thirdmoira.offer_backend.data.mappers.EntityDomainOfferMapper;
import com.thirdmoira.offer_backend.data.mappers.EntityDomainOfferVersionMapper;
import com.thirdmoira.offer_backend.domain.models.Offer;
import com.thirdmoira.offer_backend.domain.models.OfferVersion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfferRepositoryTest {
    @InjectMocks
    private OfferRepository offerRepository;
    @Mock
    private OfferJpaRepository offerJpaRepository;
    @Mock
    private OfferVersionJpaRepository offerVersionJpaRepository;
    @Mock
    private EntityDomainOfferMapper entityDomainOfferMapper;
    @Mock
    private EntityDomainOfferVersionMapper entityDomainOfferVersionMapper;

    @Test
    void should_create_offer_entity_and_create_jpa_offer_version_entity_when_create_offer() {
        //given=mock
        OfferEntity offerEntity = new OfferEntity();
        when(entityDomainOfferMapper.toEntity(any(), any(), any(), any()))
                .thenReturn(offerEntity);
        OfferEntity offerEntitySaved = new OfferEntity(1L,null);
        when(offerJpaRepository.save(offerEntity))
                .thenReturn(offerEntitySaved);
        when(entityDomainOfferMapper.toDomain(any(OfferEntity.class)))
                .thenReturn(new Offer());


        when(entityDomainOfferVersionMapper.toEntity(any(), any(), any(), any())).thenReturn(new OfferVersionEntity());
        when(offerVersionJpaRepository.save(any(OfferVersionEntity.class)))
                .thenReturn(new OfferVersionEntity());



        //when
        Offer offer = offerRepository.create("jkdjksjksd", null, "boubou", 3L,"newtitleVersion");
        //then
        verify(offerJpaRepository).save(offerEntity);
        assertEquals(Offer.class, offer.getClass());

    }

//    @Test
//    void should_update_offer_entity_and_create_offer_version_when_asked(){
//        //mock
//        when(entityDomainOfferMapper.toEntity(any(), any(), any(), any())).thenReturn(new OfferEntity());
//        when(offerJpaRepository.save(any(OfferEntity.class)))
//                .thenReturn(new OfferEntity());
//        when(entityDomainOfferVersionMapper.toEntity(any(), any(), any(), any())).thenReturn(new OfferVersionEntity());
//        when(offerVersionJpaRepository.save(any(OfferVersionEntity.class)))
//                .thenReturn(new OfferVersionEntity());
//        when(entityDomainOfferMapper.toDomain(any(), any(), any(), any())).thenReturn(new Offer());
//        when(entityDomainOfferVersionMapper.toDomain(any(), any(), any(), any())).thenReturn(new OfferVersion());
//
//        //action
//        Offer offer = offerRepository.update("jkdjksjksd", 1, "boubou", 3L);
//        OfferVersion offerVersion = offerVersionJpaRepository.increment("newtitle", offer.getId(), 3L); //title, offer_id, lastofferversionid
//
//        //verify
//        verify(offerJpaRepository).save(any(OfferEntity.class));
//        assertEquals(Offer.class, offer.getClass());
//        verify(offerVersionJpaRepository).save(any(OfferVersionEntity.class));
//        assertEquals(OfferVersion.class, offerVersion.getClass());
//    }



}