package org.unischeduler.backend.application.service.academic_programming.out.dtos;

import java.time.LocalTime;

public class GroupScheduleInfo {
    private final String groupScheduleId;
    private final String day;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String classroom;

    public GroupScheduleInfo(String groupScheduleId, String day, LocalTime startTime, LocalTime endTime, String classroom) {
        this.groupScheduleId = groupScheduleId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
    }

    public String getGroupScheduleId() {
        return groupScheduleId;
    }

    public String getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getClassroom() {
        return classroom;
    }
}
