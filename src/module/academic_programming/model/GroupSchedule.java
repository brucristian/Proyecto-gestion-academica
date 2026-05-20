package module.academic_programming.model;

import shared.enums.WeekDays;

public class GroupSchedule {
    //================// Atributos //================//
    private long groupScheduleId;
    private long groupId;
    private WeekDays dayOfWeek;
    private String startTime;
    private String endTime;
    private String classroom;

    //================// Constructores //================//


    public GroupSchedule() {
    }

    public GroupSchedule(long groupScheduleId, long groupId, WeekDays dayOfWeek, String startTime, String endTime, String classroom) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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
