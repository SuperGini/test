package com.gini.rest.dto;

import com.gini.shared.Role;

import java.util.Set;

public record UserResponse(
        String id,
        String email,
        Set<Role> roles

) {
}
