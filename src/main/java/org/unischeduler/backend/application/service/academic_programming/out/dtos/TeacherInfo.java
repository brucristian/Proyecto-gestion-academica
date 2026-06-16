package org.unischeduler.backend.application.service.academic_programming.out.dtos;

public class TeacherInfo {
    private final String teacherId;
    private final String teacherName;

    public TeacherInfo(String teacherId, String teacherName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
