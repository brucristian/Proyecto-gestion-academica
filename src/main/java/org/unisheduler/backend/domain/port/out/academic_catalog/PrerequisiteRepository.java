package org.unisheduler.backend.domain.port.out.academic_catalog;

import org.unisheduler.backend.domain.model.academic_catalog.entity.Course;
import org.unisheduler.backend.domain.model.academic_catalog.entity.Prerequisite;

import java.util.List;

public interface PrerequisiteRepository {
    List<Prerequisite> findAllPrerequisitesWhereCourseId(String courseId);
}
