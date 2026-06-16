package org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos;

public class UpdateAcademicPeriodResponse {
    private final boolean successfully;
    private final String message;
    private final AcademicPeriodInfo academicPeriod;

    public UpdateAcademicPeriodResponse(boolean successfully, String message, AcademicPeriodInfo academicPeriod) {
        this.successfully = successfully;
        this.message = message;
        this.academicPeriod = academicPeriod;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public AcademicPeriodInfo getAcademicPeriod() {
        return academicPeriod;
    }
}
