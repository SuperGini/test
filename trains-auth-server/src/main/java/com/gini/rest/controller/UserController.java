package com.gini.rest.controller;

import com.gini.rest.dto.UserRequest;
import com.gini.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.TEXT_HTML_VALUE) //returns HTML so that HTMX will work
public class UserController {

    private final UserService userService;

    @PostMapping( "/user")
    public String createUser(@ModelAttribute("userRequest") UserRequest userRequest2) {
        userService.createUser2(userRequest2);
        return "components/home/right/create/successCreateUserMessage";
    }

    @GetMapping("/user")
    public String getUsersByPartialEmail(@RequestParam String partialEmail, Model model){
        model.addAttribute("users", userService.getUserByPartialEmail(partialEmail));
        return "components/home/right/table/userRow";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "components/home/right/table/userRow";
    }

    @ResponseBody // so it passes the white space and not a div and deletes the user from the UI
    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable String userId) {
        userService.deleteUserById(userId);
        return "";
    }

}
