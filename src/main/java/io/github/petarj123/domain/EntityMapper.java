package io.github.petarj123.domain;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;

import java.lang.reflect.Field;

@ApplicationScoped
public class EntityMapper {
    public <T> void updateEntity(T entity, Object dto) throws IllegalAccessException, NoSuchFieldException {
        for (Field field : dto.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            Object value = field.get(dto);
            if (value != null) {
                Field entityField = entity.getClass().getDeclaredField(field.getName());
                entityField.setAccessible(true);

                if (field.getName().equals("password") && value instanceof String password) {
                    if (!password.startsWith("$2a$") && !password.startsWith("$2b$")) {
                        value = BcryptUtil.bcryptHash(password);
                    }
                }

                entityField.set(entity, value);
            }
        }
    }
}
