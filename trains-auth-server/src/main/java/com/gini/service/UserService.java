package com.gini.service;

import com.gini.exception.UserAlreadyExistsException;
import com.gini.persistence.model.Authority;
import com.gini.persistence.model.User;
import com.gini.persistence.repository.AuthorityRepository;
import com.gini.persistence.repository.UserRepository;
import com.gini.rest.dto.UserRequest;
import com.gini.rest.dto.UserResponse;
import com.gini.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Transactional
    public UserResponse createUser2(UserRequest userRequest2) {

        userRepository
                .findByEmail(userRequest2.getEmail())
                .ifPresent(x -> {
                    throw new UserAlreadyExistsException("User with this name exist");
                });

        var authorities = authorityRepository.getAuthority(userRequest2.getAuthorities());

        var user = createUser(userRequest2, authorities);

        var savedUser = userRepository.save(user);

        return mapToUserResponse(savedUser);
    }

    @Deprecated(forRemoval = true, since = " 10.12.2023 -> not in use anymore -> to be deleted")
    @Transactional
    public Set<UserResponse> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toSet());
    }

    @Transactional
    public Page<UserResponse> getUserByPartialEmail(String partialEmail, Integer pageNumber) {
        var nrOfElementsOnPage = 5;
        Pageable page = PageRequest.of(pageNumber, nrOfElementsOnPage);
        var usersIds = userRepository.findUsersIdByEmail(page, partialEmail);

        var users = userRepository.getUsersPaginated(usersIds).stream()
                .map(this::mapToUserResponse)
                .toList();
        var countAllUsers = userRepository.totalUsersCount2(partialEmail);

        return new PageImpl<>(users, page, countAllUsers);
    }

    @Transactional
    public void deleteUserById(String userId) {
        userRepository.deleteUserById(userId);
    }

    /**
     * F.F.F important -> https://vladmihalcea.com/join-fetch-pagination-spring/
     *
     * */
    @Transactional
    public Page<UserResponse> findAllUsersPaginated(Integer pageNumber) {
        var nrOfElementsOnPage = 5;

        Pageable page = PageRequest.of(pageNumber, nrOfElementsOnPage);

        var usersIds = userRepository.fidUsersId(page);

        var users = userRepository.getUsersPaginated(usersIds).stream()
                .map(this::mapToUserResponse)
                .toList();

        var countAllUsers = userRepository.totalUsersCount();

        return new PageImpl<>(users, page, countAllUsers);

    }

    @Transactional
    public Page <UserResponse> findallUsersPaginated(Integer pageNumber, String partialEmail) {

        if (partialEmail == null || partialEmail.isEmpty() || partialEmail.isBlank()) {
            return findAllUsersPaginated(pageNumber - 1);
        }

        return getUserByPartialEmail(partialEmail, pageNumber - 1);

    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .map(UserSecurity::new)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    private UserResponse mapToUserResponse(User savedUser) {
        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getAuthorities().stream()
                        .map(Authority::getRole)
                        .collect(Collectors.toSet())
        );
    }

    private User createUser(UserRequest userRequest, Set<Authority> authorities) {
        return User.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .authorities(authorities)
                .build()
                .addUserToAuthorities();
    }

}


