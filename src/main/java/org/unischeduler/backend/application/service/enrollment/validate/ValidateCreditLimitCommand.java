package org.unischeduler.backend.application.service.enrollment.validate;

import java.util.List;

public class ValidateCreditLimitCommand {
    private final String studentId;
    private final List<String> courseIds;

    public ValidateCreditLimitCommand(String studentId, List<String> courseIds) {
        this.studentId = studentId;
        this.courseIds = courseIds;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<String> getCourseIds() {
        return courseIds;
    }
}
