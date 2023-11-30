package com.gini.rest.dto;

import com.gini.persistence.model.Authority;

import java.util.Set;

public record UserRequest(
        String username,
        String email,
        String password,
        Set<Authority> authorities) {

}
