package com.gini.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public record HtmxController(

) {

    @GetMapping("/test")
    public String get(Model model) {


        return "test";

    }

    @ResponseBody
    @DeleteMapping(value = "/delete", produces = MediaType.TEXT_HTML_VALUE)
    public String delete () {
        System.out.println("delete***************************************************************************");

        return "";
    }






}
