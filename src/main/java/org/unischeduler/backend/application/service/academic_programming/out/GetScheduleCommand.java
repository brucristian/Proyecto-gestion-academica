package org.unischeduler.backend.application.service.academic_programming.out;

public class GetScheduleCommand {
    private final String studentId;

    public GetScheduleCommand(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }
}
