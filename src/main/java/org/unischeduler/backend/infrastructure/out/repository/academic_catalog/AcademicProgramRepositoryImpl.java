package org.unischeduler.backend.infrastructure.out.repository.academic_catalog;

import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicProgramRepository;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.AcademicProgramEntity;
import org.unischeduler.backend.infrastructure.out.mapper.academic_catalog.AcademicProgramMapper;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelAcademicProgramRepository;

import java.util.List;
import java.util.Optional;

public class AcademicProgramRepositoryImpl implements AcademicProgramRepository {
    private final ExcelAcademicProgramRepository programRepository;

    public AcademicProgramRepositoryImpl(ExcelAcademicProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Optional<AcademicProgram> findById(String id) {
        Optional<AcademicProgramEntity> entityOptional = programRepository.findById(id);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        AcademicProgramEntity entity = entityOptional.get();
        return Optional.of(AcademicProgramMapper.toDomain(entity));
    }

    @Override
    public List<AcademicProgram> findAll() {
        List<AcademicProgramEntity> entities = programRepository.findAll();

        return entities.stream()
                .map(AcademicProgramMapper::toDomain)
                .toList();
    }
}
