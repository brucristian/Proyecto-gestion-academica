package org.unischeduler.backend.application.service.academic_catalog.in.course.dtos;

public class DeleteCourseResponse {
    private final boolean successfully;
    private final String message;

    public DeleteCourseResponse(boolean successfully, String message) {
        this.successfully = successfully;
        this.message = message;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }
}
