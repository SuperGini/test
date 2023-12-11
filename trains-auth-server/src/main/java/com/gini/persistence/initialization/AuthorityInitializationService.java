package com.gini.persistence.initialization;

import com.gini.persistence.model.Authority;
import com.gini.security.Role;
import com.gini.persistence.repository.AuthorityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorityInitializationService {

    private final AuthorityRepository authorityRepository;

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

    }

}
