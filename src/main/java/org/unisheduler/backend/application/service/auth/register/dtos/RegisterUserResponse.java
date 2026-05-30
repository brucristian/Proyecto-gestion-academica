package org.unisheduler.backend.application.service.auth.register.dtos;

public class RegisterUserResponse {
    private final boolean successfully;
    private final String message;

    public RegisterUserResponse(boolean successfully, String message) {
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
