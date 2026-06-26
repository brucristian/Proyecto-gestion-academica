package org.unischeduler.backend.application.service.academic_programming.in.group_schedules;

import org.unischeduler.backend.domain.model.academic_programming.entity.GroupSchedule;
import org.unischeduler.backend.domain.model.academic_programming.enums.WeekDays;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegisterGroupScheduleCommand {
    private final String groupId;
    private final String dayOfWeak;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String classroom;

    public RegisterGroupScheduleCommand(String groupId, String dayOfWeak, LocalTime startTime, LocalTime endTime, String classroom) {
        this.groupId = groupId;
        this.dayOfWeak = dayOfWeak;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getDayOfWeak() {
        return dayOfWeak;
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

    public static GroupSchedule toDomain(RegisterGroupScheduleCommand schedule) {
        return new GroupSchedule(
                null,
                schedule.getGroupId(),
                WeekDays.valueOf(schedule.getDayOfWeak()),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getClassroom()
        );
    }

    public static RegisterGroupScheduleCommand toCommand(GroupSchedule schedule) {
        return new RegisterGroupScheduleCommand(
                schedule.getGroupId(),
                schedule.getDayOfWeek().name(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getClassroom()
        );
    }
}
