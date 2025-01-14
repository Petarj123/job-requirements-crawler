package io.github.petarj123.domain.repository;

import io.github.petarj123.domain.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public List<User> getAll() {
        return findAll().list();
    }

    public User getById(Long id) {
        return find("id", id).firstResultOptional().orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User getByEmail(String email) {
        return find("email", email).firstResultOptional().orElseThrow(() -> new NotFoundException("User not found"));
    }
}
