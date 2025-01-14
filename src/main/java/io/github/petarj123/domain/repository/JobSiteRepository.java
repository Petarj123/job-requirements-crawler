package io.github.petarj123.domain.repository;

import io.github.petarj123.domain.entity.JobSite;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JobSiteRepository implements PanacheRepository<JobSite> {
    public Optional<JobSite> getById(int id) {
        return find("id", id).firstResultOptional();
    }

    public List<JobSite> getAll() {
        return findAll().list();
    }
}
