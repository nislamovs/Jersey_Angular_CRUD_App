package com.jerseyexample.app.domain.responses;

import static java.lang.String.format;

public class ErrorResponse {

    public String errorText;

    public ErrorResponse(String message) {
        errorText = message;
    }

    public ErrorResponse(String format, Object... args) {
        this(format(format, args));
    }
}
