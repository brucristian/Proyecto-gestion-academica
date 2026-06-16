package org.unischeduler.backend.domain.model.enrollment.entity;

import org.unischeduler.backend.domain.model.auth.entity.User;

public class Student {

    //================// Atributos //================//
    private final String studentId;
    private final String studentCode;
    private final User user;


    //================// Constructores //================//
    public Student(String studentId, String studentCode, User user) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.user = user;
    }

    //================// Setters y Getters //================//

    public String getStudentId() {
        return studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public User getUser() {
        return user;
    }

}
