package org.unischeduler.backend.application.service.academic_catalog.in.course;

public class DeleteCourseCommand {
    private final String id;

    public DeleteCourseCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
