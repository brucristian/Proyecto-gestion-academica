package org.unischeduler.backend.domain.model.academic_history.entity;

import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.model.academic_history.enums.AcademicHistoryCourseStatus;
import org.unischeduler.backend.domain.model.enrollment.entity.Student;

import java.util.ArrayList;

public class AcademicHistory {

    //================// Atributos //================//
    private String historyId;
    private Student student;
    private ArrayList<Course> completedCourses;
    private double grade;
    private AcademicHistoryCourseStatus status;


    //================// Constructores //================//

    public AcademicHistory(String historyId, Student student, ArrayList<Course> completedCourses, double grade, AcademicHistoryCourseStatus status) {
        this.historyId = historyId;
        this.student = student;
        this.completedCourses = completedCourses;
        this.grade = grade;
        this.status = status;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ArrayList<Course> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(ArrayList<Course> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public AcademicHistoryCourseStatus getStatus() {
        return status;
    }

    public void setStatus(AcademicHistoryCourseStatus status) {
        this.status = status;
    }

    //================// Funciones Adicionales //================//

}
