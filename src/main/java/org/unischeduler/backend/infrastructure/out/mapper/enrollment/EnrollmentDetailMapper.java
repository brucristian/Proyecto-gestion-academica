package org.unischeduler.backend.infrastructure.out.mapper.enrollment;

import org.unischeduler.backend.domain.model.academic_programming.entity.Group;
import org.unischeduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unischeduler.backend.domain.model.enrollment.enums.EnrollmentStatus;
import org.unischeduler.backend.infrastructure.out.entity.enrollment.EnrollmentDetailEntity;

public class EnrollmentDetailMapper {

    public static EnrollmentDetailEntity toEntity(
            EnrollmentDetail detail,
            String enrollmentId
    ) {
        EnrollmentDetailEntity entity = new EnrollmentDetailEntity();

        entity.setEnrollmentDetailId(
                detail.getEnrollmentDetailId()
        );

        entity.setEnrollmentId(enrollmentId);

        entity.setGroupId(
                detail.getGroup().getGroupId()
        );

        entity.setStatus(
                detail.getStatus().name()
        );

        entity.setFinalGrade(
                detail.getFinalGrade()
        );

        return entity;
    }

    public static EnrollmentDetail toDomain(
            EnrollmentDetailEntity entity,
            Group group
    ) {
        return new EnrollmentDetail(
                entity.getEnrollmentDetailId(),
                group,
                EnrollmentStatus.valueOf(
                        entity.getStatus()
                ),
                entity.getFinalGrade()
        );
    }
}