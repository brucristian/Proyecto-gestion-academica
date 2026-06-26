package org.unischeduler.backend.application.service.academic_programming.in.group_schedules;

import org.unischeduler.backend.domain.port.in.academic_programming.group_schedule.DeleteGroupSchedulesUseCase;
import org.unischeduler.backend.domain.port.out.academic_programming.GroupScheduleRepository;

public class DeleteGroupSchedulesService
        implements DeleteGroupSchedulesUseCase {

    private final GroupScheduleRepository groupScheduleRepository;

    public DeleteGroupSchedulesService(
            GroupScheduleRepository groupScheduleRepository
    ) {
        this.groupScheduleRepository = groupScheduleRepository;
    }

    @Override
    public void execute(DeleteGroupSchedulesCommand command) {
        groupScheduleRepository.deleteAllWhereGroupId(
                command.getGroupId()
        );
    }
}