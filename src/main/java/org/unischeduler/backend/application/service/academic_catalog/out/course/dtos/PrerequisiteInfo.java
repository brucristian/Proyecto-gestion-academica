package org.unischeduler.backend.application.service.academic_catalog.out.course.dtos;

import org.unischeduler.backend.domain.model.academic_catalog.entity.Prerequisite;

public class PrerequisiteInfo {
    private final String id;
    private final String code;
    private final String name;

    public PrerequisiteInfo(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public static PrerequisiteInfo toInfo(Prerequisite prerequisite) {
        return new PrerequisiteInfo(
                prerequisite.getPrerequisite(),
                prerequisite.getCourseId(),
                prerequisite.getRequiredCourse().getName()
        );
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
