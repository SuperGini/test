package com.gini.service;

import com.gini.persistence.model.Authority;
import com.gini.persistence.repository.AuthorityRepository;
import com.gini.security.UserSecurity;
import com.gini.persistence.model.User;
import com.gini.persistence.repository.UserRepository;
import com.gini.rest.dto.UserRequest;
import com.gini.rest.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
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
    public UserResponse createUser(UserRequest userRequest) {

       var roles =  userRequest.authorities().stream().map(Authority::getRole).collect(Collectors.toSet());

       var authorities = authorityRepository.getAuthority(roles);

       var user = User.builder()
               .email(userRequest.email())
               .password(userRequest.password())
               .authorities(authorities)
               .build();

        authorities.forEach(auth -> auth.setUsers(Set.of(user)));

        var savedUser = userRepository.save(user);

        return new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getAuthorities().stream().map(Authority::getRole).collect(Collectors.toSet()));

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .map(UserSecurity::new)
                .orElseThrow(() ->new RuntimeException("User not found"));
    }
}
