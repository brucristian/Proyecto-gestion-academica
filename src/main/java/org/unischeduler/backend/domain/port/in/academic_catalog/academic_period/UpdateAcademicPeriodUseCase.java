package org.unischeduler.backend.domain.port.in.academic_catalog.academic_period;


import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.UpdateAcademicPeriodCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos.UpdateAcademicPeriodResponse;

public interface UpdateAcademicPeriodUseCase {
    UpdateAcademicPeriodResponse execute(UpdateAcademicPeriodCommand command);
}
