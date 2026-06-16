package org.unischeduler.backend.domain.model.academic_catalog.entity;

public class Prerequisite {

    //================// Atributos //================//
    private String prerequisite;
    private String courseId;
    private Course requiredCourse;


    //================// Constructores //================//

    public Prerequisite(String prerequisite, String courseId, Course requiredCourse) {
        this.prerequisite = prerequisite;
        this.courseId = courseId;
        this.requiredCourse = requiredCourse;
    }

    //================// Setters y Getters //================//

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Course getRequiredCourse() {
        return requiredCourse;
    }

    public void setRequiredCourse(Course requiredCourse) {
        this.requiredCourse = requiredCourse;
    }


    //================// Funciones Adicionales //================//
}
