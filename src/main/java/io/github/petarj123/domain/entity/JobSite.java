package io.github.petarj123.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_sites", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class JobSite {
    @NotBlank(message = "Name may not be blank")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "BaseUrl may not be blank")
    @Column(name = "base_url", nullable = false)
    private String baseUrl;

    @Column(name = "last_crawled")
    private LocalDateTime lastCrawled;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = false;

    @Column(name = "inserted_at", nullable = false)
    private LocalDateTime insertedAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected JobSite() {
    }

    public JobSite(String name, String baseUrl, LocalDateTime lastCrawled, boolean isActive) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.lastCrawled = lastCrawled;
        this.isActive = isActive;
    }

    public JobSite(String name, String baseUrl, LocalDateTime lastCrawled, boolean isActive, LocalDateTime insertedAt, LocalDateTime updatedAt) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.lastCrawled = lastCrawled;
        this.isActive = isActive;
        this.insertedAt = insertedAt;
        this.updatedAt = updatedAt;
    }

    public JobSite(LocalDateTime updatedAt, String name, String baseUrl, LocalDateTime lastCrawled, boolean isActive) {
        this.updatedAt = updatedAt;
        this.name = name;
        this.baseUrl = baseUrl;
        this.lastCrawled = lastCrawled;
        this.isActive = isActive;
    }

    public JobSite(String name, String baseUrl, LocalDateTime lastCrawled, boolean isActive, LocalDateTime insertedAt, LocalDateTime updatedAt, Long id) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.lastCrawled = lastCrawled;
        this.isActive = isActive;
        this.insertedAt = insertedAt;
        this.updatedAt = updatedAt;
        this.id = id;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(LocalDateTime insertedAt) {
        this.insertedAt = insertedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public LocalDateTime getLastCrawled() {
        return lastCrawled;
    }

    public boolean isActive() {
        return isActive;
    }
}
