package org.unischeduler.backend.application.service.enrollment.register.dtos;

public class RegisterEnrollmentResponse {
    private final boolean successfully;
    private final String message;

    public RegisterEnrollmentResponse(boolean successfully, String message) {
        this.successfully = successfully;
        this.message = message;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }
}
