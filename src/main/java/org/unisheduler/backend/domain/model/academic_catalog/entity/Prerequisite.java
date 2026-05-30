package org.unisheduler.backend.domain.model.academic_catalog.entity;

public class Prerequisite {

    //================// Atributos //================//
    private long prerequisite;
    private long courseId;
    private Course requiredCourse;


    //================// Constructores //================//

    public Prerequisite() {
    }

    public Prerequisite(long prerequisite, long courseId, Course requiredCourse) {
        this.prerequisite = prerequisite;
        this.courseId = courseId;
        this.requiredCourse = requiredCourse;
    }

    //================// Setters y Getters //================//

    public long getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(long prerequisite) {
        this.prerequisite = prerequisite;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
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
