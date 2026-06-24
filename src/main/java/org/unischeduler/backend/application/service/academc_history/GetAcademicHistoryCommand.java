package org.unischeduler.backend.application.service.academc_history;

public class GetAcademicHistoryCommand {
    private final String studentId;

    public GetAcademicHistoryCommand(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }
}
