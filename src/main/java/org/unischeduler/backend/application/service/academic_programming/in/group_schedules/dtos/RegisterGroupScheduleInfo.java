package org.unischeduler.backend.application.service.academic_programming.in.group_schedules.dtos;

import java.time.LocalTime;

public class RegisterGroupScheduleInfo {
    private final String groupScheduleId;
    private final String groupId;
    private final String day;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String classroom;

    public RegisterGroupScheduleInfo(String groupScheduleId, String groupId, String day, LocalTime startTime, LocalTime endTime, String classroom) {
        this.groupScheduleId = groupScheduleId;
        this.groupId = groupId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
    }

    public String getGroupScheduleId() {
        return groupScheduleId;
    }

    public String getGroupId() {
        return groupId;
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
