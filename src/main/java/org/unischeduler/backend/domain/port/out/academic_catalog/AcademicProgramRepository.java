package org.unischeduler.backend.domain.port.out.academic_catalog;

import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;

import java.util.List;
import java.util.Optional;

public interface AcademicProgramRepository {
    Optional<AcademicProgram> findById(String id);
    List<AcademicProgram> findAll();
}
