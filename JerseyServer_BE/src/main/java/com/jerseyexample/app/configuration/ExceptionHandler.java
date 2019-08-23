package com.jerseyexample.app.configuration;

import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.status;
import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

import javax.validation.ValidationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jerseyexample.app.domain.exceptions.ImageConversionException;
import com.jerseyexample.app.domain.exceptions.UserNotFoundException;
import com.jerseyexample.app.domain.responses.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.stereotype.Component;

@Component
@Provider
@Slf4j
public class ExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {
        ex.printStackTrace();
        if (ex instanceof ValidationException
                || ex instanceof JsonParseException
                || ex instanceof JsonMappingException
                || ex instanceof ImageConversionException) {

            return buildError(BAD_REQUEST, ex);
        }

        if (ex instanceof UserNotFoundException) {
            return buildError(NOT_FOUND, ex);
        }

        if (ex instanceof WebApplicationException) {
            WebApplicationException exception = (WebApplicationException) ex;

            if (exception.getResponse().getStatus() == BAD_GATEWAY.getStatusCode()) {
                return buildError(BAD_GATEWAY, ex);
            }

            if (exception.getResponse().getStatus() == NOT_FOUND.getStatusCode()) {
                return buildError(NOT_FOUND, ex);
            }

            return buildError(fromStatusCode(exception.getResponse().getStatus()), ex);
        }

        log.error("Rest service error", ex);
        return status(INTERNAL_SERVER_ERROR).entity(new ErrorResponse(getStackTrace(ex))).build();
    }

    private Response buildError(Status status, Throwable ex) {
        return status(status).entity(new ErrorResponse(ex.getMessage())).build();
    }
}