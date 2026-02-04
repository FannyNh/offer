package com.thirdmoira.offer_backend.data;

import com.thirdmoira.offer_backend.data.entities.GroupEntity;
import com.thirdmoira.offer_backend.data.entities.UserEntity;
import com.thirdmoira.offer_backend.data.jpa.UserJpaRepository;
import com.thirdmoira.offer_backend.data.mappers.EntityDomainOfferMapper;
import com.thirdmoira.offer_backend.data.mappers.EntityDomainUserMapper;
import com.thirdmoira.offer_backend.domain.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    @InjectMocks
    private UserRepository userRepository;
    @Mock
    private EntityDomainUserMapper entityDomainUserMapper;
    @Mock
    private UserJpaRepository jpaRepository;

    @Test
    void should_return_user_domain_when_get_user_by_uid_is_called(){
        String principal = "user-123";
        UserEntity userEntityOptional = new UserEntity();
        userEntityOptional.setUserId(1L);
        userEntityOptional.setFirstName("John");
        userEntityOptional.setLastName("Doe");
        userEntityOptional.setEmail("john.doe@mail.com");
        userEntityOptional.setIdpId(principal);
        userEntityOptional.setGroup(mock());
        when(jpaRepository.findOneByIdpId(principal)).thenReturn(Optional.of(userEntityOptional));
        User user = new User();
        user.setUserId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@mail.com");
        user.setGroupId(10L);
        when(entityDomainUserMapper.toDomain(userEntityOptional)).thenReturn(user);
        User result = userRepository.getUserByUid(principal);

        assertNotNull(result);
        assertEquals(user.getUserId(), result.getUserId());
        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getEmail(), result.getEmail());

        verify(jpaRepository).findOneByIdpId(principal);
        verify(entityDomainUserMapper).toDomain(userEntityOptional);
    }
}
