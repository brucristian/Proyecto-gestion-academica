package org.unischeduler.backend.infrastructure.out.entity.academic_programming;

public class SemesterTemplateDetailEntity {
    private String templateDetailId;
    private String templateId;
    private String groupId;

    public String getTemplateDetailId() {
        return templateDetailId;
    }

    public void setTemplateDetailId(String templateDetailId) {
        this.templateDetailId = templateDetailId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
