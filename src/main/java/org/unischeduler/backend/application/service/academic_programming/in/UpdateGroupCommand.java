package org.unischeduler.backend.application.service.academic_programming.in;

public class UpdateGroupCommand {
    private final String groupId;
    private final String courseId;
    private final String groupCode;
    private final String teacherId;
    private final int capacity;

    public UpdateGroupCommand(String groupId, String courseId, String groupCode, String teacherId, int capacity) {
        this.groupId = groupId;
        this.courseId = courseId;
        this.groupCode = groupCode;
        this.teacherId = teacherId;
        this.capacity = capacity;
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
}
