package com.gini.authority.initialization;

import com.gini.authority.model.Authority;
import com.gini.shared.Role;
import com.gini.authority.repository.AuthorityRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public record AuthorityInitializationService(

        AuthorityRepository authorityRepository

) {

    @PostConstruct
    private void initiateRoles() {
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
