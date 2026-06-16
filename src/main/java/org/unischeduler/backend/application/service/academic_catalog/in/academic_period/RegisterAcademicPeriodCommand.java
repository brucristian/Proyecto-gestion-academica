package org.unischeduler.backend.application.service.academic_catalog.in.academic_period;


import java.time.LocalDate;

public class RegisterAcademicPeriodCommand {
    private final String code;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String status;

    public RegisterAcademicPeriodCommand(String code, String name, LocalDate startDate, LocalDate endDate, String status) {
        this.code = code;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }
}
