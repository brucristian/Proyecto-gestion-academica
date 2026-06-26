package org.unischeduler.backend.application.service.auth;

public class ChangePasswordCommand {
    private final String userId;
    private final String currentPassword;
    private final String newPassword;
    private final String confirmPassword;

    public ChangePasswordCommand(String userId, String currentPassword, String newPassword, String confirmPassword) {
        this.userId = userId;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
