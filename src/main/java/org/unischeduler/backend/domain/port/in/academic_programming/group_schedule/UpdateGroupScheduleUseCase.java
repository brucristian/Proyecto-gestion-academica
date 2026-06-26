package org.unischeduler.backend.domain.port.in.academic_programming.group_schedule;

import org.unischeduler.backend.application.service.academic_programming.in.group_schedules.UpdateGroupScheduleCommand;
import org.unischeduler.backend.application.service.academic_programming.in.group_schedules.dtos.UpdateGroupScheduleResponse;

public interface UpdateGroupScheduleUseCase {
    UpdateGroupScheduleResponse execute(UpdateGroupScheduleCommand command);
}
