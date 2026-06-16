package org.unischeduler.ui.mapper;


import org.unischeduler.backend.application.service.academic_catalog.out.academic_programs.dtos.AcademicProgramInfo;
import org.unischeduler.ui.viewmodel.student.ProgramViewModel;

public final class ProgramMapper {

    private ProgramMapper() {
    }

    public static ProgramViewModel toViewModel(AcademicProgramInfo programInfo) {
        if (programInfo == null) {
            return null;
        }

        return new ProgramViewModel(
                programInfo.getAcademicProgramId(),
                programInfo.getName(),
                programInfo.getTotalSemesters()
        );
    }

    public static AcademicProgramInfo toDto(ProgramViewModel viewModel) {
        if (viewModel == null) {
            return null;
        }

        return new AcademicProgramInfo(
                viewModel.getAcademicProgramId(),
                viewModel.getName(),
                viewModel.getTotalSemesters()
        );
    }
}