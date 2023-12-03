package com.gini.service;

import com.gini.persistence.model.Authority;
import com.gini.persistence.model.User;
import com.gini.persistence.repository.AuthorityRepository;
import com.gini.persistence.repository.UserRepository;
import com.gini.rest.dto.UserRequest;
import com.gini.rest.dto.UserResponse;
import com.gini.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Transactional
    public UserResponse createUser2(UserRequest userRequest2) {

        var authorities = authorityRepository.getAuthority(userRequest2.getAuthorities());

        var user = createUser(userRequest2, authorities);

        authorities.forEach(auth -> auth.setUsers(Set.of(user)));

        var savedUser = userRepository.save(user);

        return mapToUserResponse(savedUser);

    }


    @Transactional
    public Set<UserResponse> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toSet());
    }

    @Transactional
    public Set<UserResponse> getUserByPartialEmail(String partialEmail) {
        return userRepository.findByPartialEmail(partialEmail).stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toSet());
    }

    @Transactional
    public void deleteUserById(String userId) {
        userRepository.deleteUserById(userId);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .map(UserSecurity::new)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    private UserResponse mapToUserResponse(User savedUser) {
        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getAuthorities().stream()
                        .map(Authority::getRole)
                        .collect(Collectors.toSet())
        );
    }

    private User createUser(UserRequest userRequest, Set<Authority> authorities) {
        return User.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .authorities(authorities)
                .build();
    }

}


