package org.unisheduler.backend.domain.model.academic_programming.entity;

import org.unisheduler.backend.shared.enums.WeekDays;

import java.time.LocalTime;

public class GroupSchedule {
    //================// Atributos //================//
    private long groupScheduleId;
    private long groupId;
    private WeekDays dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String classroom;

    //================// Constructores //================//


    public GroupSchedule() {
    }

    public GroupSchedule(long groupScheduleId, long groupId, WeekDays dayOfWeek, LocalTime startTime, LocalTime endTime, String classroom) {
        this.groupScheduleId = groupScheduleId;
        this.groupId = groupId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
    }


    //================// Setters y Getters //================//

    public long getGroupScheduleId() {
        return groupScheduleId;
    }

    public void setGroupScheduleId(long groupScheduleId) {
        this.groupScheduleId = groupScheduleId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public WeekDays getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(WeekDays dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }


    //================// Funciones Adicionales //================//
}
