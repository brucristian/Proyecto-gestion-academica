package org.unischeduler.backend.infrastructure.out.entity.academic_catalog;

public class AcademicPeriodEntity {
    private String academicPeriodId;
    private String code;
    private String name;
    private String startDate;
    private String endDate;
    private String status;

    public String getAcademicPeriodId() {
        return academicPeriodId;
    }

    public void setAcademicPeriodId(String academicPeriodId) {
        this.academicPeriodId = academicPeriodId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
