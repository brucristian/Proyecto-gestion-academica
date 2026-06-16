package org.unischeduler.backend.application.service.enrollment.validate;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupScheduleInfo;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidateScheduleConflictsResponse;
import org.unischeduler.backend.domain.port.in.enrollment.ValidateScheduleConflictsUseCase;

import java.util.List;
import java.util.ArrayList;

public class ValidateScheduleConflictsService
        implements ValidateScheduleConflictsUseCase {

    @Override
    public ValidateScheduleConflictsResponse execute(
            ValidateScheduleConflictsCommand command
    ) {

        List<GroupScheduleInfo> schedules =
                command.getGroupScheduleInfos();

        List<String> conflicts = new ArrayList<>();

        for (int i = 0; i < schedules.size(); i++) {

            GroupScheduleInfo current = schedules.get(i);

            for (int j = i + 1; j < schedules.size(); j++) {

                GroupScheduleInfo other = schedules.get(j);

                boolean sameDay =
                        current.getDay().equalsIgnoreCase(
                                other.getDay()
                        );

                boolean timeOverlap =
                        current.getStartTime().isBefore(
                                other.getEndTime()
                        )
                                && current.getEndTime().isAfter(
                                other.getStartTime()
                        );

                if (sameDay && timeOverlap) {

                    conflicts.add(
                            String.format(
                                    "Conflicto el %s entre %s-%s y %s-%s",
                                    current.getDay(),
                                    current.getStartTime(),
                                    current.getEndTime(),
                                    other.getStartTime(),
                                    other.getEndTime()
                            )
                    );
                }
            }
        }

        if (!conflicts.isEmpty()) {
            return new ValidateScheduleConflictsResponse(
                    false,
                    conflicts
            );
        }

        return new ValidateScheduleConflictsResponse(
                true,
                List.of("No existen conflictos de horario")
        );
    }
}