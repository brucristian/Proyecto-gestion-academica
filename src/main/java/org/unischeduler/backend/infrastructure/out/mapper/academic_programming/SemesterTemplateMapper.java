package org.unischeduler.backend.infrastructure.out.mapper.academic_programming;

import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unischeduler.backend.domain.model.academic_programming.entity.SemesterTemplate;
import org.unischeduler.backend.domain.model.academic_programming.entity.SemesterTemplateDetail;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.SemesterTemplateEntity;

import java.util.List;

public class SemesterTemplateMapper {

    public static SemesterTemplateEntity toEntity(
            SemesterTemplate template
    ) {
        SemesterTemplateEntity entity = new SemesterTemplateEntity();

        entity.setTemplateId(
                template.getTemplateId()
        );

        entity.setProgramId(
                template.getAcademicProgram().getAcademicProgramId()
        );

        entity.setSemester(
                template.getSemester()
        );

        return entity;
    }

    public static SemesterTemplate toDomain(
            SemesterTemplateEntity entity,
            AcademicProgram academicProgram,
            List<SemesterTemplateDetail> details
    ) {
        return new SemesterTemplate(
                entity.getTemplateId(),
                academicProgram,
                entity.getSemester(),
                details
        );
    }
}
