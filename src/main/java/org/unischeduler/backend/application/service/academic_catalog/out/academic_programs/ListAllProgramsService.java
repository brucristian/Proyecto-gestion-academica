package org.unischeduler.backend.application.service.academic_catalog.out.academic_programs;

import org.unischeduler.backend.application.service.academic_catalog.out.academic_programs.dtos.AcademicProgramInfo;
import org.unischeduler.backend.application.service.academic_catalog.out.academic_programs.dtos.ListAllProgramsResponse;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_program.ListAllProgramsUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicProgramRepository;

import java.util.List;

public class ListAllProgramsService implements ListAllProgramsUseCase {
    private final AcademicProgramRepository programRepository;

    public ListAllProgramsService(AcademicProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public ListAllProgramsResponse execute() {
        List<AcademicProgramInfo> programsInfo = programRepository.findAll()
                .stream()
                .map(AcademicProgramInfo::fromDomain)
                .toList();

        return new ListAllProgramsResponse(
                true,
                "Se encontraron " + programsInfo.size() + " programas academicos",
                programsInfo
        );


    }
}
