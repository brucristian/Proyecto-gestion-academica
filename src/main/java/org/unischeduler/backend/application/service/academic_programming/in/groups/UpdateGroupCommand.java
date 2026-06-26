package org.unischeduler.backend.application.service.academic_programming.in.groups;

import org.unischeduler.backend.application.service.academic_programming.in.group_schedules.RegisterGroupScheduleCommand;

import java.util.List;

public class UpdateGroupCommand {
    private final String groupId;
    private final String courseId;
    private final String groupCode;
    private final String teacherId;
    private final int capacity;
    private final List<RegisterGroupScheduleCommand> groupSchedules;

    public UpdateGroupCommand(String groupId, String courseId, String groupCode, String teacherId, int capacity, List<RegisterGroupScheduleCommand> groupSchedules) {
        this.groupId = groupId;
        this.courseId = courseId;
        this.groupCode = groupCode;
        this.teacherId = teacherId;
        this.capacity = capacity;
        this.groupSchedules = groupSchedules;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<RegisterGroupScheduleCommand> getGroupSchedules() {
        return groupSchedules;
    }
}
