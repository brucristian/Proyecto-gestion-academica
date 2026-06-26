package org.unischeduler.backend.application.service.academic_programming.in.group_schedules;

import org.unischeduler.backend.application.service.academic_programming.in.group_schedules.dtos.UpdateGroupScheduleResponse;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupScheduleInfo;
import org.unischeduler.backend.domain.model.academic_programming.entity.GroupSchedule;
import org.unischeduler.backend.domain.port.in.academic_programming.group_schedule.UpdateGroupScheduleUseCase;
import org.unischeduler.backend.domain.port.out.academic_programming.GroupScheduleRepository;

import java.util.ArrayList;
import java.util.List;

public class UpdateGroupScheduleService
        implements UpdateGroupScheduleUseCase {

    private final GroupScheduleRepository groupScheduleRepository;

    public UpdateGroupScheduleService(
            GroupScheduleRepository groupScheduleRepository
    ) {
        this.groupScheduleRepository = groupScheduleRepository;
    }

    @Override
    public UpdateGroupScheduleResponse execute(
            UpdateGroupScheduleCommand command
    ) {

        groupScheduleRepository.deleteAllWhereGroupId(
                command.getGroupId()
        );

        List<GroupScheduleInfo> scheduleInfos = new ArrayList<>();

        for (GroupSchedule schedule : command.getSchedules()) {

            GroupSchedule saved =
                    groupScheduleRepository.save(schedule);

            scheduleInfos.add(
                    new GroupScheduleInfo(
                            saved.getGroupId(),
                            null, // CourseName no está disponible aquí
                            saved.getGroupScheduleId(),
                            saved.getDayOfWeek().name(),
                            saved.getStartTime(),
                            saved.getEndTime(),
                            saved.getClassroom()
                    )
            );
        }

        return new UpdateGroupScheduleResponse(
                true,
                "Los horarios del grupo fueron actualizados correctamente.",
                scheduleInfos
        );
    }
}