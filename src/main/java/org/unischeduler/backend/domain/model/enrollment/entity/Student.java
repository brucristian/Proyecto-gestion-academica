package org.unischeduler.backend.domain.model.enrollment.entity;

import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unischeduler.backend.domain.model.auth.entity.User;

public class Student {

    //================// Atributos //================//
    private final String studentId;
    private final String studentCode;
    private final User user;
    private final AcademicProgram academicProgram;


    //================// Constructores //================//
    public Student(String studentId, String studentCode, User user, AcademicProgram academicProgram) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.user = user;
        this.academicProgram = academicProgram;
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

    public AcademicProgram getAcademicProgram() {
        return academicProgram;
    }

}
