package org.unischeduler.backend.application.service.enrollment.validate;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupScheduleInfo;

import java.util.List;

public class ValidateScheduleConflictsCommand {
    private final List<GroupScheduleInfo> groupInfos;

    public ValidateScheduleConflictsCommand(List<GroupScheduleInfo> groupInfos) {
        this.groupInfos = groupInfos;
    }

    public List<GroupScheduleInfo> getGroupScheduleInfos() {
        return groupInfos;
    }
}
