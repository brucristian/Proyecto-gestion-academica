package org.unischeduler.backend.application.service.enrollment.validate;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupScheduleInfo;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidateScheduleConflictsResponse;
import org.unischeduler.backend.domain.model.academic_programming.entity.Group;
import org.unischeduler.backend.domain.port.in.enrollment.ValidateScheduleConflictsUseCase;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;

import java.util.List;
import java.util.ArrayList;

public class ValidateScheduleConflictsService
        implements ValidateScheduleConflictsUseCase {

    private final EnrollmentRepository enrollmentRepository;

    public ValidateScheduleConflictsService(
            EnrollmentRepository enrollmentRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public ValidateScheduleConflictsResponse execute(
            ValidateScheduleConflictsCommand command
    ) {

        List<GroupScheduleInfo> schedules =
                new ArrayList<>(command.getGroupInfos());

        enrollmentRepository
                .findByStudentAndActivePeriod(
                        command.getStudentId()
                )
                .ifPresent(enrollment -> {

                    if (enrollment.getDetails() == null) {
                        return;
                    }

                    enrollment.getDetails().forEach(detail -> {

                        Group group = detail.getGroup();

                        group.getSchedules().forEach(schedule ->

                                schedules.add(
                                        new GroupScheduleInfo(
                                                group.getGroupId(),
                                                group.getCourse().getName(),
                                                schedule.getGroupScheduleId(),
                                                schedule.getDayOfWeek().name(),
                                                schedule.getStartTime(),
                                                schedule.getEndTime(),
                                                schedule.getClassroom()
                                        )
                                )
                        );
                    });
                });

        List<String> conflicts = new ArrayList<>();

        for (int i = 0; i < schedules.size(); i++) {

            GroupScheduleInfo current = schedules.get(i);

            for (int j = i + 1; j < schedules.size(); j++) {

                GroupScheduleInfo other = schedules.get(j);

                if (current.getGroupId().equals(other.getGroupId())) {
                    continue;
                }

                boolean sameDay =
                        current.getDay()
                                .equalsIgnoreCase(other.getDay());

                boolean timeOverlap =
                        current.getStartTime()
                                .isBefore(other.getEndTime())
                                &&
                                current.getEndTime()
                                        .isAfter(other.getStartTime());

                if (sameDay && timeOverlap) {

                    conflicts.add(
                            String.format(
                                    "%s entra en conflicto con %s el %s",
                                    current.getCourseName(),
                                    other.getCourseName(),
                                    current.getDay()
                            )
                    );
                }
            }
        }

        return new ValidateScheduleConflictsResponse(
                conflicts.isEmpty(),
                conflicts.isEmpty()
                        ? List.of("No existen conflictos de horario")
                        : conflicts
        );
    }
}