package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiCreateOfferRequest;
import com.thirdmoira.offer_backend.api.rest.ApiOfferEditView;
import com.thirdmoira.offer_backend.api.rest.ApiOfferLight;
import com.thirdmoira.offer_backend.data.mappers.ApiDomainOfferMapper;
import com.thirdmoira.offer_backend.domain.OfferService;
import com.thirdmoira.offer_backend.domain.UserService;
import com.thirdmoira.offer_backend.domain.models.OfferEditView;
import com.thirdmoira.offer_backend.domain.models.Offer;
import com.thirdmoira.offer_backend.domain.models.OfferVersion;
import com.thirdmoira.offer_backend.domain.models.Service;
import com.thirdmoira.offer_backend.domain.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @Mock
    private UserService userService;


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

    @Test
    void should_include_services_in_edit_view_for_owner() {
        // given
        SecurityContextHolder.getContext().setAuthentication(
                new TestingAuthenticationToken("uid-123", null, "ROLE_USER")
        );

        User user = new User();
        user.setUserId(5L);
        when(userService.getUserByUid("uid-123")).thenReturn(user);

        OfferVersion version = new OfferVersion();
        version.setVersionNumber(1L);
        Offer offer = new Offer(5L, 10L, version);

        ApiOfferLight apiOfferLight = new ApiOfferLight();
        apiOfferLight.setId(10L);
        apiOfferLight.setUserId(5L);
        apiOfferLight.setVersion(null);

        Service service = new Service();
        service.setId(99L);
        service.setTitle("Cleaning");
        service.setCategoryId(3L);
        service.setCategoryName("Housework");

        OfferEditView editView = new OfferEditView();
        editView.setOffer(offer);
        editView.setServices(List.of(service));

        when(offerService.getById(10L)).thenReturn(offer);
        when(apiDomainOfferMapper.toApi(offer)).thenReturn(apiOfferLight);
        when(offerService.getEditView(10L, 5L)).thenReturn(editView);

        // when
        ApiOfferEditView response = offerController.getOfferById(10L);

        // then
        assertNotNull(response);
        assertEquals(1, response.getServices().size());
        assertEquals("Housework", response.getServices().get(0).getCategoryName());
    }

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }
}
