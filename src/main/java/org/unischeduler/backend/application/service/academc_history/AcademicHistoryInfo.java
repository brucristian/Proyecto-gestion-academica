package org.unischeduler.backend.application.service.academc_history;

import org.unischeduler.backend.domain.model.enrollment.entity.EnrollmentDetail;

public class AcademicHistoryInfo {
    private final String code;
    private final String name;
    private final int credits;
    private final String period;
    private final double note;
    private final String status;

    public AcademicHistoryInfo(String code, String name, int credits, String period, double note, String status) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.period = period;
        this.note = note;
        this.status = status;
    }

    public static AcademicHistoryInfo fromEnrollmentDetail(EnrollmentDetail detail, String period) {
        return new AcademicHistoryInfo(
                detail.getGroup().getGroupCode(),
                detail.getGroup().getCourse().getName(),
                detail.getGroup().getCourse().getCredits(),
                period,
                detail.getFinalGrade(),
                detail.getStatus().name()
        );
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public String getPeriod() {
        return period;
    }

    public double getNote() {
        return note;
    }

    public String getStatus() {
        return status;
    }
}
