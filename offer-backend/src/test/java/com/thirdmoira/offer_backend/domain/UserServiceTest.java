package com.thirdmoira.offer_backend.domain;

import com.thirdmoira.offer_backend.data.UserRepository;
import com.thirdmoira.offer_backend.data.UserRepositoryTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    void shouldCallRepositoryWhenCreateOrUpdate() {
        //given
        //when
        userService.createOrUpdate("sesres", "boubou", "bou@gmail.com", null, null, null);
        //then
        verify(userRepository).createOrUpdate("sesres", "boubou", "bou@gmail.com", null, null, null);
    }

    @Test
    void shouldCallRepositoryWhenGetUsers() {
        //given
        //when
        userService.get();
        //then
        verify(userRepository).get();
    }

    @Test
    void shouldCallRepositoryWhenGetUserByUid() {
        userService.getUserByUid("sesres");
        verify(userRepository).getUserByUid("sesres");
    }
}