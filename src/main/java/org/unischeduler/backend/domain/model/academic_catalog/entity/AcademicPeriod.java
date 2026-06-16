package org.unischeduler.backend.domain.model.academic_catalog.entity;


import org.unischeduler.backend.domain.model.academic_catalog.enums.AcademicPeriodStatus;

import java.time.LocalDate;

public class AcademicPeriod {

    //================// Atributos //================//
    private final String academicPeriodId;
    private final String code;
    private String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private AcademicPeriodStatus status;

    //================// Constructores //================//


    public AcademicPeriod(String academicPeriodId, String code, String name, LocalDate startDate, LocalDate endDate, AcademicPeriodStatus status) {
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

    public AcademicPeriodStatus getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(AcademicPeriodStatus status) {
        this.status = status;
    }
}
