package com.gini.rest.controller;

import com.gini.rest.dto.UserRequest;
import com.gini.rest.dto.UserResponse;
import com.gini.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/user", produces = MediaType.TEXT_HTML_VALUE)
    public String createUser(@ModelAttribute("userRequest") UserRequest userRequest2) {
        userService.createUser2(userRequest2);
        return "components/home/right/create/successCreateUserMessage";
    }

    @GetMapping(value = "/user", produces = MediaType.TEXT_HTML_VALUE)
    public String getUsersByPartialEmail(@RequestParam String partialEmail, @RequestParam(defaultValue = "1") Integer pageNumber,  Model model) {
        var usersPaginated = userService.getUserByPartialEmail(partialEmail, pageNumber - 1);

        setModelAttributes(pageNumber, model, usersPaginated);

        return "components/home/right/table/userRow";
    }

    @ResponseBody // so it passes the white space and not a div and deletes the user from the UI
    @DeleteMapping(value = "/user/{userId}", produces = MediaType.TEXT_HTML_VALUE)
    public String deleteUser(@PathVariable String userId) {
        userService.deleteUserById(userId);
        return "";
    }

    @GetMapping(value = "/users", produces = MediaType.TEXT_HTML_VALUE)
    public String findAllUsersPaginated(@RequestParam(defaultValue = "1") Integer pageNumber, Model model, @RequestParam(required = false) String partialEmail) {

        var usersPaginated = userService.findallUsersPaginated(pageNumber, partialEmail);

        setModelAttributes(pageNumber, model, usersPaginated);

        return "components/home/right/table/userRow";
    }

    private void setModelAttributes(Integer pageNumber, Model model, Page<UserResponse> usersPaginated) {
        model
                .addAttribute("users", usersPaginated.getContent())
                .addAttribute("currentPage", pageNumber)
                .addAttribute("totalItems", usersPaginated.getTotalElements())
                .addAttribute("totalPages", usersPaginated.getTotalPages())
                .addAttribute("previous", usersPaginated.hasPrevious())
                .addAttribute("next", usersPaginated.hasNext());
    }

}
