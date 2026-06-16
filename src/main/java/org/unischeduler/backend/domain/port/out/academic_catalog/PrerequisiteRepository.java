package org.unischeduler.backend.domain.port.out.academic_catalog;

import org.unischeduler.backend.domain.model.academic_catalog.entity.Prerequisite;

import java.util.List;

public interface PrerequisiteRepository {
    List<Prerequisite> findAllPrerequisitesWhereCourseId(String courseId);
    Prerequisite save(String courseId, String coursePrerequisiteId);
    boolean deleteWhereCourseId(String id);
}
