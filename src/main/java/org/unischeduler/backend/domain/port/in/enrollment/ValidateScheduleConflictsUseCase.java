package org.unischeduler.backend.domain.port.in.enrollment;

import org.unischeduler.backend.application.service.enrollment.validate.ValidateScheduleConflictsCommand;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidateScheduleConflictsResponse;

public interface ValidateScheduleConflictsUseCase {
    ValidateScheduleConflictsResponse execute(ValidateScheduleConflictsCommand command);
}
