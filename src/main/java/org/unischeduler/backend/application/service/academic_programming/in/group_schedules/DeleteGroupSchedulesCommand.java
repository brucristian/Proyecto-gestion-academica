package org.unischeduler.backend.application.service.academic_programming.in.group_schedules;

public class DeleteGroupSchedulesCommand {
    private final String groupId;

    public DeleteGroupSchedulesCommand(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }
}
