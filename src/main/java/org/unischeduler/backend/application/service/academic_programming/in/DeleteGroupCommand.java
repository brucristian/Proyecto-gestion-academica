package org.unischeduler.backend.application.service.academic_programming.in;

public class DeleteGroupCommand {
    private final String groupId;

    public DeleteGroupCommand(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }
}
