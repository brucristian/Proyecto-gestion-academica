package org.unischeduler.backend.domain.port.in.academic_catalog.academic_period;

import org.unischeduler.backend.application.service.academic_catalog.out.academic_period.dtos.ListAllAcademicPeriodsResponse;

public interface ListAllAcademicPeriodsUseCase {
    ListAllAcademicPeriodsResponse execute();
}
