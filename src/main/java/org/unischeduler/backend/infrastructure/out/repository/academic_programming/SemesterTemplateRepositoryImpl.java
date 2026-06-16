package org.unischeduler.backend.infrastructure.out.repository.academic_programming;

import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unischeduler.backend.domain.model.academic_programming.entity.SemesterTemplate;
import org.unischeduler.backend.domain.model.academic_programming.entity.SemesterTemplateDetail;
import org.unischeduler.backend.domain.port.out.academic_programming.SemesterTemplateDetailRepository;
import org.unischeduler.backend.domain.port.out.academic_programming.SemesterTemplateRepository;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.AcademicProgramEntity;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.SemesterTemplateEntity;
import org.unischeduler.backend.infrastructure.out.mapper.academic_catalog.AcademicProgramMapper;
import org.unischeduler.backend.infrastructure.out.mapper.academic_programming.SemesterTemplateMapper;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_program.ExcelSemesterTemplateRepository;

import java.util.List;

public class SemesterTemplateRepositoryImpl implements SemesterTemplateRepository {
    private final ExcelSemesterTemplateRepository semesterTemplateRepository;
    private final SemesterTemplateDetailRepository semesterTemplateDetailRepository;

    public SemesterTemplateRepositoryImpl(ExcelSemesterTemplateRepository semesterTemplateRepository, SemesterTemplateDetailRepository semesterTemplateDetailRepository) {
        this.semesterTemplateRepository = semesterTemplateRepository;
        this.semesterTemplateDetailRepository = semesterTemplateDetailRepository;
    }

    @Override
    public SemesterTemplate findByProgramAndSemester(AcademicProgram program, int semester) {
        AcademicProgramEntity academicProgramEntity = AcademicProgramMapper.toEntity(program);
        SemesterTemplateEntity entity = semesterTemplateRepository.findByProgramAndSemester(academicProgramEntity, semester);

        List<SemesterTemplateDetail> templateDetails = semesterTemplateDetailRepository.findAllWhereTemplateId(entity.getTemplateId());
        return SemesterTemplateMapper.toDomain(
                entity,
                program,
                templateDetails
        );
    }
}
