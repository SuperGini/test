package com.gini.persistence.repository;

import com.gini.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("""
                SELECT u FROM User u JOIN FETCH u.authorities WHERE u.email =:email
            """)
    Optional<User> findUserByEmail(String email);

    @Query("""
                SELECT u FROM User u JOIN FETCH u.authorities
            """)
    Set<User> getAllUsers();

    @Query("""
                SELECT u FROM User u JOIN FETCH u.authorities WHERE u.email LIKE %:partialEmail%
            """)
    Set<User> findByPartialEmail(String partialEmail);

    @Query("""
                SELECT u FROM User u WHERE u.email =:email
            """)
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("""
                DELETE FROM User u WHERE u.id = :userId
            """)
    void deleteUserById(String userId);


}
