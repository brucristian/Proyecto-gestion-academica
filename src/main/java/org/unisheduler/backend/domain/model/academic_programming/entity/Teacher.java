package org.unisheduler.backend.domain.model.academic_programming.entity;

public class Teacher {

    //================// Atributos //================//
    private long teacherId;
    private String firstName;
    private String lastName;

    //================// Constructores //================//

    public Teacher() {
    }

    public Teacher(long teacherId, String firstName, String lastName) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //================// Setters y Getters //================//

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    //================// Funciones Adicionales //================//



}
