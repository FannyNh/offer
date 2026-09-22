package com.thirdmoira.offer_backend.api;

import com.thirdmoira.offer_backend.api.rest.ApiUser;
import com.thirdmoira.offer_backend.data.mappers.ApiDomainUserMapper;
import com.thirdmoira.offer_backend.domain.UserService;
import com.thirdmoira.offer_backend.domain.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private ApiDomainUserMapper apiDomainUserMapper;

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void should_return_api_user_when_get_me_is_called() {
        // GIVEN: un utilisateur authentifié
        String principal = "user-123";

        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(principal);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Domain User (mocké)
        User user = new User();
        user.setUserId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@mail.com");
        user.setGroupId(10L);

        // ApiUser (mocké)
        ApiUser api = new ApiUser();
        api.setUserId(1L);
        api.setFirstName("John");
        api.setLastName("Doe");
        api.setEmail("john@mail.com");
        api.setGroupId(10L);

        // Mocks
        when(userService.getUserByUid(principal)).thenReturn(user);
        when(apiDomainUserMapper.toApi(user)).thenReturn(api);

        // WHEN: on appelle le controller
        ApiUser result = userController.getMe();

        // THEN: on vérifie le résultat
        assertNotNull(result);
        assertEquals(api, result);

        // Vérifie que les méthodes mockées ont été appelées
        verify(userService).getUserByUid(principal);
        verify(apiDomainUserMapper).toApi(user);
    }
}
