package org.unischeduler.backend.domain.model.academic_programming.entity;

public class SemesterTemplateDetail {
    private String templateDetailId;
    private Group group;

    public SemesterTemplateDetail(String templateDetailId, Group group) {
        this.templateDetailId = templateDetailId;
        this.group = group;
    }

    public String getTemplateDetailId() {
        return templateDetailId;
    }

    public void setTemplateDetailId(String templateDetailId) {
        this.templateDetailId = templateDetailId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
