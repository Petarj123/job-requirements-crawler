package io.github.petarj123.crawler.service;

import io.github.petarj123.common.dto.UserDTO;
import io.github.petarj123.common.exception.ValidationException;
import io.github.petarj123.domain.EntityMapper;
import io.github.petarj123.domain.entity.User;
import io.github.petarj123.domain.repository.UserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    @Inject
    Validator validator;

    @Inject
    EntityMapper entityMapper;

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Transactional
    public User createUser(UserDTO userDTO) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(userDTO.email(), userDTO.password(), now, now);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }

        user.setPassword(BcryptUtil.bcryptHash(user.getPassword()));

        userRepository.persist(user);
        return user;
    }

    @Transactional
    public User updateUser(Long id, UserDTO userDTO) throws NoSuchFieldException, IllegalAccessException {
        User user = userRepository.getById(id);
        entityMapper.updateEntity(user, userDTO);
        user.setUpdatedAt(LocalDateTime.now());

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }

        userRepository.persist(user);
        return user;
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }
}
