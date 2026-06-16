package org.unischeduler.backend.application.service.academic_programming.in.dtos;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupInfo;

public class RegisterGroupResponse {
    private final boolean successfully;
    private final String message;
    private final GroupInfo group;

    public RegisterGroupResponse(boolean successfully, String message, GroupInfo group) {
        this.successfully = successfully;
        this.message = message;
        this.group = group;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public GroupInfo getGroup() {
        return group;
    }
}
