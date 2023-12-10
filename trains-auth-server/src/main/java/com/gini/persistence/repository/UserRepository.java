package com.gini.persistence.repository;

import com.gini.persistence.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
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


    @Query("""
                SELECT u FROM User u JOIN FETCH u.authorities ORDER BY u.email ASC
            """)
    Page<User> findAllUsersPaginated(Pageable pageable);

    @Query("""
                SELECT u.id FROM User u
            """)
    List<String> fidUsersId(Pageable pageable);


    @Query("""
    SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.authorities WHERE u.id IN :userIds ORDER BY u.email ASC
""")
    List<User> getUsersPaginated(List<String> userIds);


    @Query("""
    SELECT COUNT(u) from User u
""")
    Long totalUsersCount ();



    @Query("""
    SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.authorities WHERE u.id IN (SELECT u.id FROM User u ORDER BY u.email ASC)
""")
    List<User> xt (Pageable pageable);


}
