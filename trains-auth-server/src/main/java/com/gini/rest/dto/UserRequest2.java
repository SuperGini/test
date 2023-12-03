package com.gini.rest.dto;

import com.gini.persistence.model.Authority;
import com.gini.shared.Role;
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
public class UserRequest2 {

    private String username;
    private String email;
    private String password;
    private Set<Role> authorities = new HashSet<>();

}
