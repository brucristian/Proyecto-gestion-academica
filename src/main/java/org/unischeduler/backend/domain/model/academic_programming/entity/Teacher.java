package org.unischeduler.backend.domain.model.academic_programming.entity;

public class Teacher {

    //================// Atributos //================//
    private String teacherId;
    private String name;

    //================// Constructores //================//

    public Teacher(String teacherId, String name) {
        this.teacherId = teacherId;
        this.name = name;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getName() {
        return name;
    }

    //================// Funciones Adicionales //================//



}
