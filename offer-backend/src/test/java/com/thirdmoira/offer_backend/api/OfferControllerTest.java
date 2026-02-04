package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiCreateOfferRequest;
import com.thirdmoira.offer_backend.api.rest.ApiOfferLight;
import com.thirdmoira.offer_backend.data.mappers.ApiDomainOfferMapper;
import com.thirdmoira.offer_backend.domain.OfferService;
import com.thirdmoira.offer_backend.domain.models.Offer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OfferControllerTest {

    @InjectMocks
    private OfferController offerController;

    @Mock
    private OfferService offerService;

    @Mock
    private ApiDomainOfferMapper apiDomainOfferMapper;


    @Test
    void should_return_api_offer_when_create_offer_is_called() {
        // given
        ApiCreateOfferRequest request = mock(ApiCreateOfferRequest.class);

        Offer domainOffer = mock(Offer.class);
        ApiOfferLight apiOffer = new ApiOfferLight();

        when(offerService.create(
                request.getName(),
                request.getId(),
                request.getDescription(),
                request.getUserId(),
                request.getTitle(),
                request.getStatus()
        )).thenReturn(domainOffer);

        when(apiDomainOfferMapper.toApi(domainOffer)).thenReturn(apiOffer);

        // when
        ApiOfferLight result = offerController.createOffer(request);

        // then
        assertNotNull(result);
        assertEquals(apiOffer, result);
        verify(offerService).create(
                request.getName(),
                request.getId(),
                request.getDescription(),
                request.getUserId(),
                request.getTitle(),
                request.getStatus()
        );
        verify(apiDomainOfferMapper).toApi(domainOffer);
    }


    @Test
    void should_return_api_offer_when_update_offer_is_called() {
        // given
        ApiCreateOfferRequest request = mock(ApiCreateOfferRequest.class);

        Offer domainOffer = mock(Offer.class);
        ApiOfferLight apiOffer = new ApiOfferLight();

        when(offerService.update(
                request.getName(),
                request.getId(),
                request.getDescription(),
                request.getUserId(),
                request.getTitle(),
                request.getStatus()
        )).thenReturn(domainOffer);

        when(apiDomainOfferMapper.toApi(domainOffer)).thenReturn(apiOffer);

        // when
        ApiOfferLight result = offerController.updateOffer(request);

        // then
        assertNotNull(result);
        assertEquals(apiOffer, result);
        verify(offerService).update(
                request.getName(),
                request.getId(),
                request.getDescription(),
                request.getUserId(),
                request.getTitle(),
                request.getStatus()
        );
        verify(apiDomainOfferMapper).toApi(domainOffer);
    }
}
