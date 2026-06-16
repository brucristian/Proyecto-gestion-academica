package org.unischeduler.backend.infrastructure.out.entity.academic_catalog;

public class PrerequisiteEntity {
    private String prerequisiteId;
    private String courseId;
    private String prerequisiteCourseId;

    public String getPrerequisiteId() {
        return prerequisiteId;
    }

    public void setPrerequisiteId(String prerequisiteId) {
        this.prerequisiteId = prerequisiteId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPrerequisiteCourseId() {
        return prerequisiteCourseId;
    }

    public void setPrerequisiteCourseId(String prerequisiteCourseId) {
        this.prerequisiteCourseId = prerequisiteCourseId;
    }
}
