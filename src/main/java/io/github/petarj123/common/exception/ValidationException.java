package io.github.petarj123.common.exception;

import jakarta.validation.ConstraintViolation;

import java.util.Set;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {
    private final Set<String> violations;

    public <T> ValidationException(Set<ConstraintViolation<T>> violations) {
        this.violations = violations.stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toSet());
    }

    public Set<String> getViolations() {
        return violations;
    }
}
