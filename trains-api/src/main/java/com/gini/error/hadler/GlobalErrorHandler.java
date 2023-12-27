package com.gini.error.hadler;

import com.gini.error.exception.TrainsCoreClientException;
import com.gini.error.exception.TrainsCoreServerException;
import gini.trainsapi.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.math.BigDecimal;

@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TrainsCoreClientException.class)
    public ErrorResponse handleTrainsCoreClientException(TrainsCoreClientException ex) {
        log.error("Trains core client error:", ex);
        return new ErrorResponse()
                .errorMessage(ex.getMessage())
                .status(BigDecimal.valueOf(HttpStatus.BAD_REQUEST.value()));
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TrainsCoreServerException.class)
    public ErrorResponse handleTrainsCoreServerException(TrainsCoreServerException ex) {
        log.error("Trains core server error:", ex);
        return new ErrorResponse()
                .errorMessage(ex.getMessage())
                .status(BigDecimal.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception ex) {
        log.error("Server error:", ex);
        return new ErrorResponse()
                .errorMessage(ex.getMessage())
                .status(BigDecimal.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }


}
