package com.gini.user.model;

import com.gini.authority.model.Authority;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

/**
 * don't fuck around with @OneToMany, etc. unidirectional mapping. Allways go for bidirectional -> best performance
 * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
 *
 * */

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "users")
@Table(indexes = @Index(name = "index_email", columnList = "emails"))
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
    private Set<Authority> roles = new HashSet<>();


}
