package org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.dtos;

import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.PrerequisiteInfo;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Prerequisite;

import java.util.List;

public class FindAllPrerequisitesResponse {

    private final List<PrerequisiteInfo> prerequisites;

    public FindAllPrerequisitesResponse(List<PrerequisiteInfo> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public List<PrerequisiteInfo> getPrerequisites() {
        return prerequisites;
    }
}