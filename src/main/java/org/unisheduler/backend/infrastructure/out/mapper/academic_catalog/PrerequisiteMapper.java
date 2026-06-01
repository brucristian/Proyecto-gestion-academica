package org.unisheduler.backend.infrastructure.out.mapper.academic_catalog;

import org.unisheduler.backend.domain.model.academic_catalog.entity.Course;
import org.unisheduler.backend.domain.model.academic_catalog.entity.Prerequisite;
import org.unisheduler.backend.infrastructure.out.entity.academic_catalog.PrerequisiteEntity;

public class PrerequisiteMapper {

    private PrerequisiteMapper() {
    }

    public static Prerequisite toDomain(
            PrerequisiteEntity entity,
            Course requiredCourse
    ) {
        if (entity == null) {
            return null;
        }

        return new Prerequisite(
                entity.getPrerequisiteId(),
                entity.getCourseId(),
                requiredCourse
        );
    }

    public static PrerequisiteEntity toEntity(
            Prerequisite prerequisite
    ) {
        if (prerequisite == null) {
            return null;
        }

        PrerequisiteEntity entity = new PrerequisiteEntity();

        entity.setPrerequisiteId(prerequisite.getPrerequisite());
        entity.setCourseId(prerequisite.getCourseId());
        entity.setPrerequisiteCourseId(
                prerequisite.getRequiredCourse().getCourseId()
        );

        return entity;
    }
}