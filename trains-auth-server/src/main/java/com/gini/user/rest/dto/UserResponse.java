package com.gini.user.rest.dto;

import com.gini.shared.Role;

import java.util.Set;

public record UserResponse(
        String id,
        String email,
        Set<Role> roles

) {
}
