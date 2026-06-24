package org.unischeduler.backend.domain.model.academic_programming.entity;

import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class Group {

    //================// Atributos //================//
    private String groupId;
    private Course course;
    private String groupCode;
    private Teacher teacher;
    private int capacity;
    private List<GroupSchedule> schedules;


    //================// Constructores //================//

    public Group() {
    }

    public Group(String groupId, Course course, String groupCode, Teacher teacher, int capacity, List<GroupSchedule> schedules) {
        this.groupId = groupId;
        this.course = course;
        this.groupCode = groupCode;
        this.teacher = teacher;
        this.capacity = capacity;
        this.schedules = schedules;
    }

    //================// Setters y Getters //================//

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
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

    public Group setCapacity(int capacity) {
        this.capacity = capacity;

        return this;
    }

    public List<GroupSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<GroupSchedule> schedules) {
        this.schedules = schedules;
    }


    //================// Funciones Adicionales //================//
}
