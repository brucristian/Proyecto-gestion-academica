package org.unisheduler.backend.domain.model.academic_programming.entity;

import org.unisheduler.backend.domain.model.academic_catalog.entity.Course;

import java.util.ArrayList;

public class Group {

    //================// Atributos //================//
    private long groupId;
    private Course course;
    private String groupCode;
    private Teacher teacher;
    private int capacity;
    private int availableQuotas;
    private ArrayList<GroupSchedule> schedules;


    //================// Constructores //================//

    public Group() {
    }

    public Group(long groupId, Course course, String groupCode, Teacher teacher, int capacity, int availableQuotas, ArrayList<GroupSchedule> schedules) {
        this.groupId = groupId;
        this.course = course;
        this.groupCode = groupCode;
        this.teacher = teacher;
        this.capacity = capacity;
        this.availableQuotas = availableQuotas;
        this.schedules = schedules;
    }

    //================// Setters y Getters //================//

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableQuotas() {
        return availableQuotas;
    }

    public void setAvailableQuotas(int availableQuotas) {
        this.availableQuotas = availableQuotas;
    }

    public ArrayList<GroupSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<GroupSchedule> schedules) {
        this.schedules = schedules;
    }


    //================// Funciones Adicionales //================//
}
