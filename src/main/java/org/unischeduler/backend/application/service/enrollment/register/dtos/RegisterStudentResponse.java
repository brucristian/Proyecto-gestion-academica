package org.unischeduler.backend.application.service.enrollment.register.dtos;

public class RegisterStudentResponse {
    private final boolean successfully;
    private final String message;
    private final StudentInfo student;

    public RegisterStudentResponse(boolean successfully, String message, StudentInfo student) {
        this.successfully = successfully;
        this.message = message;
        this.student = student;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public StudentInfo getStudent() {
        return student;
    }
}
