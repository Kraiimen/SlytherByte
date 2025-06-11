package com.slytherin.slytherbyte.models.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Class<?> entityClass, int id) {
        super(String.format("The entity %s with id %d does not exist", entityClass.getSimpleName(), id));
    }
}
