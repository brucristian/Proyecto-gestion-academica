package org.unischeduler.backend.infrastructure.out.entity.academic_programming;

public class SemesterTemplateEntity {
    private String templateId;
    private String programId;
    private int semester;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
