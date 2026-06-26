package org.unischeduler.backend.application.service.auth;

public class ChangePasswordResponse {
    private final boolean successfully;
    private final String message;

    public ChangePasswordResponse(boolean successfully, String message) {
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
