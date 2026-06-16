package org.unischeduler.backend.infrastructure.out.entity.enrollment;

public class EnrollmentDetailEntity {
    private String enrollmentDetailId;
    private String enrollmentId;
    private String groupId;
    private String status;
    private Double finalGrade;

    public String getEnrollmentDetailId() {
        return enrollmentDetailId;
    }

    public void setEnrollmentDetailId(String enrollmentDetailId) {
        this.enrollmentDetailId = enrollmentDetailId;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }
}
