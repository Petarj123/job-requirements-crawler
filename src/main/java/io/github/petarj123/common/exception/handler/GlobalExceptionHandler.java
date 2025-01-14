package io.github.petarj123.common.exception.handler;

import io.github.petarj123.common.dto.ErrorResponse;
import io.github.petarj123.common.dto.ValidationErrorResponse;
import io.github.petarj123.common.exception.ValidationException;
import io.vertx.core.json.Json;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    private static final Map<Class<? extends Exception>, Integer> ERROR_MAPPING = Map.of(
            ValidationException.class, 400,
            IllegalArgumentException.class, 400,
            NotFoundException.class, 404,
            IllegalAccessException.class, 400,
            NoSuchFieldException.class, 400,
            Exception.class, 500
    );

    @Override
    public Response toResponse(Exception e) {
        int statusCode = getStatusCode(e);

        Object responseBody = (e instanceof ValidationException ve)
                ? new ValidationErrorResponse(ve.getViolations())
                : new ErrorResponse(e.getMessage());

        return Response.status(statusCode)
                .entity(Json.encode(responseBody))
                .build();
    }

    private int getStatusCode(Exception e) {
        if (isPersistenceException(e)) {
            return 422;
        }

        return ERROR_MAPPING.getOrDefault(e.getClass(), 500);
    }

    private boolean isPersistenceException(Exception e) {
        return PersistenceException.class.isAssignableFrom(e.getClass());
    }
}
