package org.unischeduler.backend.domain.exceptions.academic_catalog;

public class PeriodActiveNotFoundException extends RuntimeException {
    public PeriodActiveNotFoundException(String message) {
        super(message);
    }
}
