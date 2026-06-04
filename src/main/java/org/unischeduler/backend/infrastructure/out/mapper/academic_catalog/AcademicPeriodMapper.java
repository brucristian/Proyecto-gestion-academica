package org.unischeduler.backend.infrastructure.out.mapper.academic_catalog;


import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicPeriod;
import org.unischeduler.backend.domain.model.academic_catalog.enums.AcademicPeriodStatus;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.AcademicPeriodEntity;

import java.time.LocalDate;

public class AcademicPeriodMapper {

    public static AcademicPeriod toDomain(AcademicPeriodEntity entity) {

        if (entity == null) {
            return null;
        }

        return new AcademicPeriod(
                entity.getAcademicPeriodId(),
                entity.getCode(),
                entity.getName(),
                LocalDate.parse(entity.getStartDate()),
                LocalDate.parse(entity.getEndDate()),
                AcademicPeriodStatus.valueOf(entity.getStatus())
        );
    }

    public static AcademicPeriodEntity toEntity(AcademicPeriod academicPeriod) {

        if (academicPeriod == null) {
            return null;
        }

        AcademicPeriodEntity entity = new AcademicPeriodEntity();

        entity.setAcademicPeriodId(academicPeriod.getAcademicPeriodId());
        entity.setCode(academicPeriod.getCode());
        entity.setName(academicPeriod.getName());
        entity.setStartDate(academicPeriod.getStartDate().toString());
        entity.setEndDate(academicPeriod.getEndDate().toString());
        entity.setStatus(academicPeriod.getStatus().name());

        return entity;
    }
}
