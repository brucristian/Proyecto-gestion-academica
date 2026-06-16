package org.unischeduler.backend.domain.model.academic_programming.entity;

import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;

import java.util.List;

public class SemesterTemplate {
    private String templateId;
    private AcademicProgram academicProgram;
    private int semester;
    private List<SemesterTemplateDetail> details;

    public SemesterTemplate(String templateId, AcademicProgram academicProgram, int semester, List<SemesterTemplateDetail> details) {
        this.templateId = templateId;
        this.academicProgram = academicProgram;
        this.semester = semester;
        this.details = details;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public AcademicProgram getAcademicProgram() {
        return academicProgram;
    }

    public void setAcademicProgram(AcademicProgram academicProgram) {
        this.academicProgram = academicProgram;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public List<SemesterTemplateDetail> getDetails() {
        return details;
    }

    public void setDetails(List<SemesterTemplateDetail> details) {
        this.details = details;
    }
}
