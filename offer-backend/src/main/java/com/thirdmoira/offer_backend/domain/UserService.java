package com.thirdmoira.offer_backend.domain;

import com.thirdmoira.offer_backend.data.UserRepository;
import com.thirdmoira.offer_backend.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createOrUpdate(String firstName,
                               String lastName,
                               String email,
                               Long groupId,
                               Long userId,
                               String idpId) {
        return userRepository.createOrUpdate(firstName,
                lastName,
                email,
                groupId,
                userId,
                idpId);
    }

    public List<User> get() {
        return userRepository.get();
    }

    public void delete(long id) {
        userRepository.delete(id);
    }
}
