package com.gini.error.response;

public record ErrorResponse(
        String errorMessage,
        int status
) {
}
