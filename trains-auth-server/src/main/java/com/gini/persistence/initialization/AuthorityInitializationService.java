package com.gini.persistence.initialization;

import com.gini.persistence.model.Authority;
import com.gini.persistence.model.User;
import com.gini.persistence.repository.AuthorityRepository;
import com.gini.persistence.repository.UserRepository;
import com.gini.security.Role;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorityInitializationService {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void initiateRoles() {
        var databaseRoles = authorityRepository.findAll().stream()
                .map(Authority::getRole)
                .collect(Collectors.toSet());

        Role.getAllRoles()
                .forEach(role -> {
                    if (!databaseRoles.contains(role)) {
                        var authority = new Authority();
                        authority.setRole(role);
                        authorityRepository.save(authority);
                    }
                });

        var users = userRepository.findAll();
        Authority authority = new Authority();
        authority.setRole(Role.ADMIN);

        if (users.isEmpty()) {
            var testUser = User.builder()
                    .authorities(Set.of(authority))
                    .email("gini@gmail.com")
                    .password("123")
                   // .password(new BCryptPasswordEncoder().encode("123"))
                    .build();
            userRepository.save(testUser);
        }
    }

}
