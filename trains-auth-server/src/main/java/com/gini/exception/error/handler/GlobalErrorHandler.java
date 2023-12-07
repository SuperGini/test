package com.gini.exception.error.handler;

import com.gini.exception.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExistException(Model model, UserAlreadyExistsException ex) {
        log.error("Error: ", ex);
        model.addAttribute("error", ex.getMessage());
        return "components/home/errors/userAlreadyExistsError";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleUserAlreadyExistException(Exception ex) {
        log.error("Error: ", ex);
        return "components/home/errors/internalServerError";
    }

}
