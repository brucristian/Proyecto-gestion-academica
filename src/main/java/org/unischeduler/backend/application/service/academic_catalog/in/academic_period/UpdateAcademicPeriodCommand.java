package org.unischeduler.backend.application.service.academic_catalog.in.academic_period;

import java.time.LocalDate;

public class UpdateAcademicPeriodCommand {
    private final String academicPeriodId;
    private final String code;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String status;

    public UpdateAcademicPeriodCommand(String academicPeriodId, String code, String name, LocalDate startDate, LocalDate endDate, String status) {
        this.academicPeriodId = academicPeriodId;
        this.code = code;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getAcademicPeriodId() {
        return academicPeriodId;
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
