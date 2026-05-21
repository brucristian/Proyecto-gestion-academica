package core.validations;

import module.academic_programming.model.Group;
import module.academic_programming.model.GroupSchedule;
import shared.exceptions.ScheduleConflictException;

import java.util.List;

public class ScheduleValidator {

    public void validate(
            Group targetGroup,
            List<Group> currentGroups
    ) {

        // Recorre los grupos actuales del estudiante
        for (Group currentGroup : currentGroups) {
            // Recorre los horarios del grupo en iteracion
            for (GroupSchedule currentSchedule : currentGroup.getSchedules()) {
                // Recorre los horarios del grupo que el estudiante quiere regitrar
                for (GroupSchedule targetSchedule : targetGroup.getSchedules()) {

                    boolean sameDay =
                            currentSchedule.getDayOfWeek()
                                    .equals(targetSchedule.getDayOfWeek());

                    boolean overlap =
                            currentSchedule.getStartTime()
                                    .isBefore(targetSchedule.getEndTime())
                                    &&
                                    targetSchedule.getStartTime()
                                            .isBefore(currentSchedule.getEndTime());

                    if (sameDay && overlap) {
                        throw new ScheduleConflictException(
                                "Hay un cruce de horario"
                        );
                    }
                }
            }
        }
    }
}