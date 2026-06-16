package org.unischeduler.backend.application.service.enrollment.register.dtos;

public class StudentInfo {
    private final String userId;
    private final String studentId;
    private final String studentCode;
    private final String studentPassword;

    public StudentInfo(String userId, String studentId, String studentCode, String studentPassword) {
        this.userId = userId;
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.studentPassword = studentPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public String getStudentPassword() {
        return studentPassword;
    }
}
