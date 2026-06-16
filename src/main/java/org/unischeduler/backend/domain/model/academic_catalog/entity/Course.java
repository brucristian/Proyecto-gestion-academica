package org.unischeduler.backend.domain.model.academic_catalog.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {

    //================// Atributos //================//
    private String courseId;
    private String name;
    private String code;
    private int credits;
    private String description;

    private List<Course> prerequisites;


    public Course(String courseId, String name, String code, int credits, String description, ArrayList<Course> prerequisites) {
        this.courseId = courseId;
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.description = description;
        this.prerequisites = prerequisites != null? prerequisites : new ArrayList<>();
    }

    //================// Setters y Getters //================//

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }


    //================// Funciones Adicionales //================//
}
