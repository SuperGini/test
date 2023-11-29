package com.gini.user.service;

import com.gini.authority.model.Authority;
import com.gini.authority.repository.AuthorityRepository;
import com.gini.user.model.User;
import com.gini.user.repository.UserRepository;
import com.gini.user.rest.dto.UserRequest;
import com.gini.user.rest.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;



    @Transactional
    public UserResponse createUser(UserRequest userRequest) {

       var roles =  userRequest.authorities().stream().map(Authority::getRole).collect(Collectors.toSet());

       var authorities = authorityRepository.getAuthority(roles);

       var user = User.builder()
               .email(userRequest.email())
               .password(userRequest.password())
               .roles(authorities)
               .build();

        authorities.forEach(auth -> auth.setUsers(Set.of(user)));

        var savedUser = userRepository.save(user);

        return new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getRoles().stream().map(Authority::getRole).collect(Collectors.toSet()));

    }






}
