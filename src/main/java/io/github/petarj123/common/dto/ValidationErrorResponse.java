package io.github.petarj123.common.dto;

import java.util.Set;

public record ValidationErrorResponse(Set<String> errors) {
}
