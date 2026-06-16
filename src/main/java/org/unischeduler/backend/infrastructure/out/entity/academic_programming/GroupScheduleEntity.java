package org.unischeduler.backend.infrastructure.out.entity.academic_programming;

import java.time.LocalTime;

public class GroupScheduleEntity {
    private String groupScheduleId;
    private String groupId;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String classroom;

    public String getGroupScheduleId() {
        return groupScheduleId;
    }

    public void setGroupScheduleId(String groupScheduleId) {
        this.groupScheduleId = groupScheduleId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
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
}
