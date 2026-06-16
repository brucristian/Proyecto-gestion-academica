package org.unischeduler.backend.infrastructure.out.entity.academic_history;

import java.util.List;

public class AcademicHistoryEntity {

    private String historyId;
    private String studentId;
    private List<String> completedCoursesId;
    private double grade;
    private String status;

    public AcademicHistoryEntity() {}

    public AcademicHistoryEntity(
            String historyId,
            String studentId,
            List<String> completedCoursesId,
            double grade,
            String status
    ) {
        this.historyId = historyId;
        this.studentId = studentId;
        this.completedCoursesId = completedCoursesId;
        this.grade = grade;
        this.status = status;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<String> getCompletedCoursesId() {
        return completedCoursesId;
    }

    public void setCompletedCourses(List<String> completedCourseId) {
        this.completedCoursesId = completedCourseId;
    }

    public void setCompletedCoursesId(List<String> completedCoursesId) {
        this.completedCoursesId = completedCoursesId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
