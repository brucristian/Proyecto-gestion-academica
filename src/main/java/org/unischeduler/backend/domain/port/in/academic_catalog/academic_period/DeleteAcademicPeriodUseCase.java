package org.unischeduler.backend.domain.port.in.academic_catalog.academic_period;

import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.DeleteAcademicPeriodCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos.DeleteAcademicPeriodResponse;

public interface DeleteAcademicPeriodUseCase {
    DeleteAcademicPeriodResponse execute(DeleteAcademicPeriodCommand command);
}
