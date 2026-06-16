package org.unischeduler.backend.application.service.academic_catalog.out.academic_period;

import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos.AcademicPeriodInfo;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicPeriod;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.ListAllAcademicPeriodsUseCase;
import org.unischeduler.backend.application.service.academic_catalog.out.academic_period.dtos.ListAllAcademicPeriodsResponse;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicPeriodRepository;

import java.util.List;

public class ListAllAcademicPeriodService implements ListAllAcademicPeriodsUseCase {
    private final AcademicPeriodRepository academicPeriodRepository;

    public ListAllAcademicPeriodService(AcademicPeriodRepository academicPeriodRepository) {
        this.academicPeriodRepository = academicPeriodRepository;
    }

    @Override
    public ListAllAcademicPeriodsResponse execute() {
        List<AcademicPeriod> academicPeriods = academicPeriodRepository.findAll();

        List<AcademicPeriodInfo> academicPeriodInfos = academicPeriods
                .stream()
                .map(AcademicPeriodInfo::fromDomain)
                .toList();

        return new ListAllAcademicPeriodsResponse(
                true,
                "Se encontraron " + academicPeriodInfos.size() + " periodos academicos",
                academicPeriodInfos
        );
    }
}
