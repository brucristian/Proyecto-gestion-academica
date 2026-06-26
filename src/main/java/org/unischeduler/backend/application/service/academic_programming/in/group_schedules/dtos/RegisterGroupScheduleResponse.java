package org.unischeduler.backend.application.service.academic_programming.in.group_schedules.dtos;


public class RegisterGroupScheduleResponse {
    private final boolean successfully;
    private final String message;
    private final RegisterGroupScheduleInfo groupSchedule;

    public RegisterGroupScheduleResponse(boolean successfully, String message, RegisterGroupScheduleInfo groupSchedule) {
        this.successfully = successfully;
        this.message = message;
        this.groupSchedule = groupSchedule;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public RegisterGroupScheduleInfo getGroupSchedule() {
        return groupSchedule;
    }
}
