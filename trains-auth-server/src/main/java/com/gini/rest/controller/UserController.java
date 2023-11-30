package com.gini.rest.controller;

import com.gini.rest.dto.UserRequest;
import com.gini.rest.dto.UserResponse;
import com.gini.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record UserController(
        UserService userService
) {

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest userRequest){
         return userService.createUser(userRequest);
    }


}
