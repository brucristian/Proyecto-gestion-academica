package org.unischeduler.backend.application.service.academic_catalog.in.course;

import java.util.List;

public class RegisterCourseCommand {
    private final String courseCode;
    private final String name;
    private final int credits;
    private final String description;
    private final List<String> prerequisiteCourseCodes;

    public RegisterCourseCommand(String courseCode, String name, int credits, String description, List<String> prerequisiteCourseCodes) {
        this.courseCode = courseCode;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.prerequisiteCourseCodes = prerequisiteCourseCodes;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getPrerequisiteCourseCodes() {
        return prerequisiteCourseCodes;
    }
}
