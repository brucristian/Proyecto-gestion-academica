package module.enrollment.model;

import module.authentication.model.User;
import module.academic_catalog.model.AcademicProgram;

public class Student {

    //================// Atributos //================//
    private final long studentId;
    private final String studentCode;
    private final User user;
    private AcademicProgram academicProgram;


    //================// Constructores //================//
    public Student(long studentId, String studentCode, User user, AcademicProgram academicProgram) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.user = user;
        this.academicProgram = academicProgram;
    }

    //================// Setters y Getters //================//

    public long getStudentId() {
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

    public void setAcademicProgram(AcademicProgram academicProgram) {
        this.academicProgram = academicProgram;
    }

}
