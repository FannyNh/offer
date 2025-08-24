package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiCreateOrUpdateOfferRequest;
import com.thirdmoira.offer_backend.api.rest.ApiOffer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OfferControllerTest {

    @InjectMocks
    private OfferController offerController;


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

}