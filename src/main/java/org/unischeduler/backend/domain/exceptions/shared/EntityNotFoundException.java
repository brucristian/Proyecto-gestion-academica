package org.unischeduler.backend.domain.exceptions.shared;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
