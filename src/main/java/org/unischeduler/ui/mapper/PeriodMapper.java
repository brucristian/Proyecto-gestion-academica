package org.unischeduler.ui.mapper;

import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos.AcademicPeriodInfo;
import org.unischeduler.ui.viewmodel.period.PeriodViewModel;

import java.util.List;

public final class PeriodMapper {

    private PeriodMapper() {
    }

    public static PeriodViewModel toViewModel(AcademicPeriodInfo info) {
        if (info == null) {
            return null;
        }

        return new PeriodViewModel(
                info.getAcademicPeriodId(),
                info.getCode(),
                info.getName(),
                info.getStartDate(),
                info.getEndDate(),
                info.getStatus()
        );
    }

    public static AcademicPeriodInfo toDto(PeriodViewModel viewModel) {
        if (viewModel == null) {
            return null;
        }

        return new AcademicPeriodInfo(
                viewModel.getAcademicPeriodId(),
                viewModel.getCode(),
                viewModel.getName(),
                viewModel.getStartDate(),
                viewModel.getEndDate(),
                viewModel.getStatus()
        );
    }

    public static List<PeriodViewModel> toViewModels(List<AcademicPeriodInfo> infos) {
        return infos.stream()
                .map(PeriodMapper::toViewModel)
                .toList();
    }

    public static List<AcademicPeriodInfo> toDtos(List<PeriodViewModel> viewModels) {
        return viewModels.stream()
                .map(PeriodMapper::toDto)
                .toList();
    }
}