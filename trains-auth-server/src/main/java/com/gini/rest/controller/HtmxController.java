package com.gini.rest.controller;

import com.gini.rest.dto.UserRequest2;
import com.gini.rest.dto.UserSearch;
import com.gini.shared.Role;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * https://www.wimdeblauwe.com/blog/2021/10/04/todomvc-with-thymeleaf-and-htmx/
 */

@Controller
public record HtmxController() {

    @GetMapping("/test")
    public String get(Model model) {


        return "test";

    }

    @ResponseBody
    @DeleteMapping(value = "/delete", produces = MediaType.TEXT_HTML_VALUE)
    public String delete() {
        System.out.println("delete***************************************************************************");

        return "";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping(value = "/home", produces = MediaType.TEXT_HTML_VALUE)
    public String home(Model model) {

        return "homePage";
    }

    @GetMapping(value = "/create", produces = MediaType.TEXT_HTML_VALUE)
    public String createPage(Model model) {
        model.addAttribute("userRequest", new UserRequest2());
        model.addAttribute("roles", Role.values());
        return "components/home/right/create/createUser";
    }

    @GetMapping(path = "/home-right", produces = MediaType.TEXT_HTML_VALUE)
    public String homeContainer(Model model) {
        model.addAttribute("userSearch", new UserSearch());
        return "components/home/home";
    }

    @GetMapping(path = "/left", produces = MediaType.TEXT_HTML_VALUE)
    public String leftContainer() {
        return "components/home/left/left";
    }


}
