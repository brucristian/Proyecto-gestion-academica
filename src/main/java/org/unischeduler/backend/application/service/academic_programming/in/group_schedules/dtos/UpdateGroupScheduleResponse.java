package org.unischeduler.backend.application.service.academic_programming.in.group_schedules.dtos;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupScheduleInfo;
import org.unischeduler.backend.domain.model.academic_programming.entity.GroupSchedule;

import java.util.List;

public class UpdateGroupScheduleResponse {
    private final boolean successfully;
    private final String message;
    private final List<GroupScheduleInfo> groupSchedules;

    public UpdateGroupScheduleResponse(boolean successfully, String message, List<GroupScheduleInfo> groupSchedules) {
        this.successfully = successfully;
        this.message = message;
        this.groupSchedules = groupSchedules;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public List<GroupScheduleInfo> getGroupSchedules() {
        return groupSchedules;
    }
}
