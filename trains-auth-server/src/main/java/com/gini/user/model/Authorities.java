package com.gini.user.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Embeddable
public class Authorities {

    private Set<Role> roles = new HashSet<>();

}
