package org.unischeduler.backend.application.service.auth.login.dtos;

public class UserInfo {
    private final String userId;
    private final String fullName;
    private final String email;
    private final UserRoleInfo userRoleInfo;
    private final String role;

    public UserInfo(String userId, String fullName, String email, UserRoleInfo userRoleInfo, String role) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.userRoleInfo = userRoleInfo;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public UserRoleInfo getUserRoleInfo() {
        return userRoleInfo;
    }

    public String getRole() {
        return role;
    }
}
