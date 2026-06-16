package org.unischeduler.backend.domain.exceptions.academic_catalog;

public class CourseCodeAlreadyExistException extends RuntimeException {
    public CourseCodeAlreadyExistException(String message) {
        super(message);
    }
}
