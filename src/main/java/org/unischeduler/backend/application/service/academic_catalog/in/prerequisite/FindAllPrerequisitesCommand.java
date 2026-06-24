package org.unischeduler.backend.application.service.academic_catalog.in.prerequisite;

public class FindAllPrerequisitesCommand {

    private final String courseId;

    public FindAllPrerequisitesCommand(
            String courseId
    ) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }
}