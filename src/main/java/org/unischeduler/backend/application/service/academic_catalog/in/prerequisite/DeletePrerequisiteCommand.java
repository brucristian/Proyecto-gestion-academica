package org.unischeduler.backend.application.service.academic_catalog.in.prerequisite;

public class DeletePrerequisiteCommand {
    private final String courseId;

    public DeletePrerequisiteCommand(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }
}