package org.unischeduler.backend.infrastructure.out.entity.enrollment;


import java.time.LocalDate;

public class EnrollmentEntity {
    private String enrollmentId;
    private String studentId;
    private String academicProgramId;
    private LocalDate enrollmentDate;
    private String academicPeriodId;

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAcademicProgramId() {
        return academicProgramId;
    }

    public void setAcademicProgramId(String academicProgramId) {
        this.academicProgramId = academicProgramId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getAcademicPeriodId() {
        return academicPeriodId;
    }

    public void setAcademicPeriodId(String academicPeriodId) {
        this.academicPeriodId = academicPeriodId;
    }
}
