package org.unischeduler.backend.application.service.academic_programming.in.dtos;

public class DeleteGroupResponse {
    private final boolean successfully;
    private final String message;

    public DeleteGroupResponse(boolean successfully, String message) {
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
