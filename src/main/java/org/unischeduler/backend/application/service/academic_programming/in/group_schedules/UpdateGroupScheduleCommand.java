package org.unischeduler.backend.application.service.academic_programming.in.group_schedules;

import org.unischeduler.backend.domain.model.academic_programming.entity.GroupSchedule;

import java.util.List;

public class UpdateGroupScheduleCommand {
    private final String groupId;
    private final List<GroupSchedule> schedules;

    public UpdateGroupScheduleCommand(String groupId, List<GroupSchedule> schedules) {
        this.groupId = groupId;
        this.schedules = schedules;
    }

    public String getGroupId() {
        return groupId;
    }

    public List<GroupSchedule> getSchedules() {
        return schedules;
    }
}
