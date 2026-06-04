package org.unisheduler.backend.application.service.academic_programming.out.dtos;

import java.time.LocalTime;

public class GroupScheduleInfo {
    private final String groupScheduleId;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String classroom;

    public GroupScheduleInfo(String groupScheduleId, LocalTime startTime, LocalTime endTime, String classroom) {
        this.groupScheduleId = groupScheduleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
    }

    public String getGroupScheduleId() {
        return groupScheduleId;
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
