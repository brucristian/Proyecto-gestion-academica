package org.unischeduler.backend.application.service.auth.login.dtos;

public class UserRoleInfo {
    private final String userRoleId;
    private final String userRoleCode;

    public UserRoleInfo(String userRoleId, String userRoleCode) {
        this.userRoleId = userRoleId;
        this.userRoleCode = userRoleCode;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public String getUserRoleCode() {
        return userRoleCode;
    }
}
