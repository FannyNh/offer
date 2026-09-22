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
import static org.mockito.Mockito.*;

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
        when(entityDomainOfferMapper.toEntity(any(), any(), eq(null)))
                .thenReturn(offerEntity);
        OfferEntity offerEntitySaved = mock(OfferEntity.class);
        when(offerJpaRepository.save(offerEntity))
                .thenReturn(offerEntitySaved);
        when(entityDomainOfferMapper.toDomain(any(OfferEntity.class)))
                .thenReturn(mock(Offer.class));


        OfferVersionEntity offerVersionEntity = new OfferVersionEntity();
        when(entityDomainOfferVersionMapper.toEntity(any(), any(), any(), any(),any())).thenReturn(offerVersionEntity);
        when(offerVersionJpaRepository.save(any(OfferVersionEntity.class)))
                .thenReturn(offerVersionEntity);



        //when
        Offer offer = offerRepository.create("jkdjksjksd", null, "boubou", 3L,"newtitleVersion",null);
        //then
        verify(offerJpaRepository).save(offerEntity);
        verify(offerVersionJpaRepository).save(offerVersionEntity);
        assertEquals(Offer.class, offer.getClass());

    }





}