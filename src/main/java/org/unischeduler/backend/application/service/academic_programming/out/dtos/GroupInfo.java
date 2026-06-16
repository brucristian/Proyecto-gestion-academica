package org.unischeduler.backend.application.service.academic_programming.out.dtos;

import java.util.List;

public class GroupInfo {
    private final String groupId;
    private final CourseInfo courseInfo;
    private final String groupCode;
    private final TeacherInfo teacherInfo;
    private final int capacity;
    private final List<GroupScheduleInfo> schedules;

    public GroupInfo(String groupId, CourseInfo courseInfo, String groupCode, TeacherInfo teacherInfo, int capacity, List<GroupScheduleInfo> schedules) {
        this.groupId = groupId;
        this.courseInfo = courseInfo;
        this.groupCode = groupCode;
        this.teacherInfo = teacherInfo;
        this.capacity = capacity;
        this.schedules = schedules;
    }

    public String getGroupId() {
        return groupId;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public TeacherInfo getTeacherInfo() {
        return teacherInfo;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<GroupScheduleInfo> getSchedules() {
        return schedules;
    }
}
