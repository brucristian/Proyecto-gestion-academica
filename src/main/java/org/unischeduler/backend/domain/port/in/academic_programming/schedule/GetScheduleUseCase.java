package org.unischeduler.backend.domain.port.in.academic_programming.schedule;

import org.unischeduler.backend.application.service.academic_programming.out.GetScheduleCommand;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.GetScheduleResponse;

public interface GetScheduleUseCase {
    GetScheduleResponse execute(GetScheduleCommand command);
}
