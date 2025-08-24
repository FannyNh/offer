package com.thirdmoira.offer_backend.api;

// Import des annotations nécessaires de Spring

import com.thirdmoira.offer_backend.api.rest.ApiCreateOrUpdateUserRequest;
import com.thirdmoira.offer_backend.api.rest.ApiUser;
import com.thirdmoira.offer_backend.data.mappers.ApiDomainUserMapper;
import com.thirdmoira.offer_backend.domain.UserService;
import com.thirdmoira.offer_backend.domain.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Annotation pour dire que cette classe est un contrôleur REST
@RestController
// Spécifie la route de base pour toutes les requêtes traitées par ce contrôleur
@RequestMapping("/api/users")
// permet de generer les logs
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApiDomainUserMapper apiDomainUserMapper;

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ApiUser createOrUpdateUser(@RequestBody ApiCreateOrUpdateUserRequest request) {
        log.info("request creat user :" + request);
        // Retourne le user creer
        User newUser = userService.createOrUpdate(request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getGroupId(),
                request.getUserId());

        return apiDomainUserMapper.toApi(newUser);
    }

    @GetMapping(produces = "application/json")
    public List<ApiUser> getUsers() {
        log.info("get test");
        List<User> users = userService.get();

        return users.stream()
                .map(apiDomainUserMapper::toApi)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        log.info("delete test");
        userService.delete(id);

    }


}
