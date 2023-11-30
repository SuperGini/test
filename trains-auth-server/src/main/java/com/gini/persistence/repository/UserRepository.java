package com.gini.persistence.repository;

import com.gini.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("""
    SELECT u FROM User u JOIN FETCH u.authorities WHERE u.email =:email
""")
    Optional<User> findUserByEmail(String email);


}
