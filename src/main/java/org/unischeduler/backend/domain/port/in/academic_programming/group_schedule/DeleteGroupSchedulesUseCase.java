package org.unischeduler.backend.domain.port.in.academic_programming.group_schedule;

import org.unischeduler.backend.application.service.academic_programming.in.group_schedules.DeleteGroupSchedulesCommand;

public interface DeleteGroupSchedulesUseCase {
    void execute(DeleteGroupSchedulesCommand command);
}
