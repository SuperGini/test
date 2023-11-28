package com.gini.user.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

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
    private UUID id;

    @EqualsAndHashCode.Include
    @Column(name = "emails")
    private String email;

    @Column(name = "password")
    private String password;

    @Embedded
    private Authorities authorities;


}
