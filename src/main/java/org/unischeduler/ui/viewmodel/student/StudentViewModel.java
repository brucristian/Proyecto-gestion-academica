package org.unischeduler.ui.viewmodel.student;

public class StudentViewModel {
    private String userId;
    private String studentId;
    private String studentCode;
    private String studentPassword;

    public StudentViewModel(String userId, String studentId, String studentCode, String studentPassword) {
        this.userId = userId;
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.studentPassword = studentPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }
}
