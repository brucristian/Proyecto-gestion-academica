package org.unischeduler.backend.application.service.auth.login.dtos;

public class LoginUserResponse {
    private final boolean successfully;
    private final String message;
    private final UserInfo user;

    public LoginUserResponse(boolean successfully, String message, UserInfo user) {
        this.successfully = successfully;
        this.message = message;
        this.user = user;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public UserInfo getUser() {
        return user;
    }
}
