package org.unischeduler.backend.application.service.academic_programming.out.dtos;

import java.util.List;

public class GetScheduleResponse {
    private final boolean successfully;
    private final String message;
    private final List<ScheduleInfo> schedules;

    public GetScheduleResponse(boolean successfully, String message, List<ScheduleInfo> schedules) {
        this.successfully = successfully;
        this.message = message;
        this.schedules = schedules;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public List<ScheduleInfo> getSchedules() {
        return schedules;
    }
}
