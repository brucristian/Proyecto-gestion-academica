package org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos;

public class DeleteAcademicPeriodResponse {
    private final boolean successfully;
    private final String message;

    public DeleteAcademicPeriodResponse(boolean successfully, String message) {
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
