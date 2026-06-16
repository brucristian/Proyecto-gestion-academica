package org.unischeduler.backend.application.service.academic_catalog.in.course.dtos;


import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.CourseInfo;

public class UpdateCourseResponse {
    private final boolean successfully;
    private final String message;
    private final CourseInfo course;

    public UpdateCourseResponse(boolean successfully, String message, CourseInfo course) {
        this.successfully = successfully;
        this.message = message;
        this.course = course;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public CourseInfo getCourse() {
        return course;
    }
}
