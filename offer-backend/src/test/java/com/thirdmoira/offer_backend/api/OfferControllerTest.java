package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiCreateOrUpdateOfferRequest;
import com.thirdmoira.offer_backend.api.rest.ApiOffer;
import com.thirdmoira.offer_backend.data.mappers.ApiDomainOfferMapper;
import com.thirdmoira.offer_backend.domain.OfferService;
import com.thirdmoira.offer_backend.domain.models.Offer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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


    @Disabled
    @Test
    public void should_return_api_offer_and_create_in_domain_when_create_or_update_offer_is_called_in_controller() {
        //given
        ApiCreateOrUpdateOfferRequest request = new ApiCreateOrUpdateOfferRequest();

        //when
        ApiOffer orUpdateOffer = offerController.createOrUpdateOffer(request);

        //then
        assertNotNull(orUpdateOffer);
//        verify(service).createOrUpdateOffer();
    }

    @Test
    void should_return_list_offer_when_get_offers_is_called() {
        // given
        Offer offer1 = mock(Offer.class);
        Offer offer2 = mock(Offer.class);
        List<Offer> offers = List.of(offer1, offer2);

        ApiOffer apiOffer1 = new ApiOffer();
        ApiOffer apiOffer2 = new ApiOffer();
        List<ApiOffer> apiOffers = List.of(apiOffer1, apiOffer2);

        // mock du service et du mapper
        when(offerService.get()).thenReturn(offers);
        when(apiDomainOfferMapper.toApi(offer1)).thenReturn(apiOffer1);
        when(apiDomainOfferMapper.toApi(offer2)).thenReturn(apiOffer2);

        // when
        List<ApiOffer> result = offerController.getOffers();

        // then
        assertNotNull(result);
        assertEquals(apiOffers.size(), result.size());
        verify(offerService).get();
    }


}