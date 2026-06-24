package org.unischeduler.backend.application.service.enrollment.register;

import java.util.List;

public class RegisterEnrollmentCommand {
    private final String studentId;
    private final List<String> groupIds;

    public RegisterEnrollmentCommand(String studentId, List<String> groupIds) {
        this.studentId = studentId;
        this.groupIds = groupIds;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }
}
