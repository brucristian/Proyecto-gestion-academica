package org.unischeduler.backend.application.service.enrollment.validate;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupScheduleInfo;

import java.util.List;

public class ValidateScheduleConflictsCommand {
    private final String studentId;
    private final List<GroupScheduleInfo> groupInfos;

    public ValidateScheduleConflictsCommand(String studentId, List<GroupScheduleInfo> groupInfos) {
        this.studentId = studentId;
        this.groupInfos = groupInfos;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<GroupScheduleInfo> getGroupInfos() {
        return groupInfos;
    }
}
