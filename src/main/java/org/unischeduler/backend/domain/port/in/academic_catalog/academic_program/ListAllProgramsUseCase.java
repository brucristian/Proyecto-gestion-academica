package org.unischeduler.backend.domain.port.in.academic_catalog.academic_program;

import org.unischeduler.backend.application.service.academic_catalog.out.academic_programs.dtos.ListAllProgramsResponse;

public interface ListAllProgramsUseCase {
    ListAllProgramsResponse execute();
}
