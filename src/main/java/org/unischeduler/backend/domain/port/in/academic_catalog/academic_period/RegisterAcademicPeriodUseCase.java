package org.unischeduler.backend.domain.port.in.academic_catalog.academic_period;

import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.RegisterAcademicPeriodCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos.RegisterAcademicPeriodResponse;

public interface RegisterAcademicPeriodUseCase {
    RegisterAcademicPeriodResponse execute(RegisterAcademicPeriodCommand command);
}
