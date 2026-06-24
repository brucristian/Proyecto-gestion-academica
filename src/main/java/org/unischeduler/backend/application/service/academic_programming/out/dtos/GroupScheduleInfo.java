package org.unischeduler.backend.application.service.academic_programming.out.dtos;

import java.time.LocalTime;

public class GroupScheduleInfo {
    private final String groupId;
    private final String courseName;

    private final String groupScheduleId;
    private final String day;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String classroom;

    public GroupScheduleInfo(String groupId, String courseName, String groupScheduleId, String day, LocalTime startTime, LocalTime endTime, String classroom) {
        this.groupId = groupId;
        this.courseName = courseName;
        this.groupScheduleId = groupScheduleId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getCourseName() {
        return courseName;
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
