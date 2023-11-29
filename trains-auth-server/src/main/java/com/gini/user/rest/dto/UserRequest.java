package com.gini.user.rest.dto;

import com.gini.authority.model.Authority;

import java.util.Set;

public record UserRequest(
        String username,
        String email,
        String password,
        Set<Authority> authorities) {

}
