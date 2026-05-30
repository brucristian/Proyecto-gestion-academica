package org.unisheduler.backend.domain.model.academic_history.entity;

import org.unisheduler.backend.domain.model.academic_catalog.entity.Course;

public class CompletedCourse {

    //================// Atributos //================//
    private Course course;
    private double grade;
    private boolean isApproved;

    //================// Constructores //================//

    public CompletedCourse() {
    }

    public CompletedCourse(Course course, double grade, boolean isApproved) {
        this.course = course;
        this.grade = grade;
        this.isApproved = isApproved;
    }

    //================// Setters y Getters //================//

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }


    //================// Funciones Adicionales //================//
}
