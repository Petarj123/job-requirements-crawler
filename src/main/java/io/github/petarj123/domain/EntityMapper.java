package io.github.petarj123.domain;

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
                entityField.set(entity, value);
            }

        }

    }
}
