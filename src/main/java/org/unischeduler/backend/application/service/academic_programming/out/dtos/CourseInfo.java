package org.unischeduler.backend.application.service.academic_programming.out.dtos;

public class CourseInfo {
    private final String courseId;
    private final String courseCode;
    private final String courseName;

    public CourseInfo(String courseId, String courseCode, String courseName) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }
}
