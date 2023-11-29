package com.gini.shared;

import java.util.Set;

public enum Role {

    ADMIN,
    USER;


    public static Set<Role> getAllRoles() {
        return Set.of(Role.values());
    }

}
