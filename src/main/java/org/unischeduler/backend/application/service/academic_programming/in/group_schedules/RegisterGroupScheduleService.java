package org.unischeduler.backend.application.service.academic_programming.in.group_schedules;

import org.unischeduler.backend.application.service.academic_programming.in.group_schedules.dtos.RegisterGroupScheduleInfo;
import org.unischeduler.backend.application.service.academic_programming.in.group_schedules.dtos.RegisterGroupScheduleResponse;
import org.unischeduler.backend.domain.model.academic_programming.entity.GroupSchedule;
import org.unischeduler.backend.domain.model.academic_programming.enums.WeekDays;
import org.unischeduler.backend.domain.port.in.academic_programming.group_schedule.RegisterGroupScheduleUseCase;
import org.unischeduler.backend.domain.port.out.academic_programming.GroupScheduleRepository;

import java.util.List;

public class RegisterGroupScheduleService implements RegisterGroupScheduleUseCase {

    private final GroupScheduleRepository groupScheduleRepository;

    public RegisterGroupScheduleService(
            GroupScheduleRepository groupScheduleRepository
    ) {
        this.groupScheduleRepository = groupScheduleRepository;
    }

    @Override
    public RegisterGroupScheduleResponse execute(
            RegisterGroupScheduleCommand command
    ) {

        if (!command.getStartTime().isBefore(command.getEndTime())) {
            return new RegisterGroupScheduleResponse(
                    false,
                    "La hora de inicio debe ser menor que la hora de finalización.",
                    null
            );
        }

        WeekDays day = WeekDays.valueOf(command.getDayOfWeak().toUpperCase());

        List<GroupSchedule> schedules =
                groupScheduleRepository.findAllWhereGroupId(command.getGroupId());

        for (GroupSchedule schedule : schedules) {

            if (schedule.getDayOfWeek() != day) {
                continue;
            }

            boolean overlaps =
                    command.getStartTime().isBefore(schedule.getEndTime())
                            && command.getEndTime().isAfter(schedule.getStartTime());

            if (overlaps) {
                return new RegisterGroupScheduleResponse(
                        false,
                        "El horario se superpone con otro horario del grupo.",
                        null
                );
            }
        }

        GroupSchedule groupSchedule = new GroupSchedule(
                null,
                command.getGroupId(),
                day,
                command.getStartTime(),
                command.getEndTime(),
                command.getClassroom()
        );

        GroupSchedule saved = groupScheduleRepository.save(groupSchedule);

        RegisterGroupScheduleInfo info =
                new RegisterGroupScheduleInfo(
                        saved.getGroupScheduleId(),
                        saved.getGroupId(),
                        saved.getDayOfWeek().name(),
                        saved.getStartTime(),
                        saved.getEndTime(),
                        saved.getClassroom()
                );

        return new RegisterGroupScheduleResponse(
                true,
                "Horario registrado correctamente.",
                info
        );
    }
}
