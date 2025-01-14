package io.github.petarj123.common.dto;

import java.time.LocalDateTime;

public record JobSiteDTO(String name, String baseUrl, LocalDateTime lastCrawled, boolean isActive) {
}
