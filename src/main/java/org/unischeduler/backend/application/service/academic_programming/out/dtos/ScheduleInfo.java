package org.unischeduler.backend.application.service.academic_programming.out.dtos;

import org.unischeduler.backend.domain.model.academic_programming.entity.GroupSchedule;
import org.unischeduler.ui.viewmodel.schedule.DayOfWeek;

import java.time.LocalTime;

public class ScheduleInfo {
    private final String courseName;
    private final String room;
    private final String day;
    private final LocalTime start;
    private final LocalTime end;

    public ScheduleInfo(String courseName, String room, String day, LocalTime start, LocalTime end) {
        this.courseName = courseName;
        this.room = room;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public static ScheduleInfo toScheduleInfo(GroupSchedule gs, String courseName) {

        return new ScheduleInfo(
                courseName,
                gs.getClassroom(),
                gs.getDayOfWeek().name(),
                gs.getStartTime(),
                gs.getEndTime()
        );
    }

    public String getCourseName() {
        return courseName;
    }

    public String getRoom() {
        return room;
    }

    public String getDay() {
        return day;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }
}
