package com.gini.rest.dto;

import com.gini.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String username;
    private String email;
    private String password;
    private Set<Role> authorities = new HashSet<>();

}
