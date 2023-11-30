package com.gini.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

/**
 * don't fuck around with @OneToMany, etc. unidirectional mapping. Allways go for bidirectional -> best performance
 * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
 *
 * */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users",indexes = @Index(name = "index_email", columnList = "emails"))
public class User {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private String id;

    @EqualsAndHashCode.Include
    @Column(name = "emails", nullable = false)
    private String email;

    @Column(name = "passwords", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Authority> authorities = new HashSet<>();


}
