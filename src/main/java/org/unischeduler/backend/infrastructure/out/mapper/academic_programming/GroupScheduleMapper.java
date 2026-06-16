package org.unischeduler.backend.infrastructure.out.mapper.academic_programming;

import org.unischeduler.backend.domain.model.academic_programming.entity.GroupSchedule;
import org.unischeduler.backend.domain.model.academic_programming.enums.WeekDays;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.GroupScheduleEntity;

public class GroupScheduleMapper {

    public static GroupScheduleEntity toEntity(GroupSchedule schedule) {
        GroupScheduleEntity entity = new GroupScheduleEntity();

        entity.setGroupScheduleId(
                String.valueOf(schedule.getGroupScheduleId())
        );

        entity.setGroupId(
                String.valueOf(schedule.getGroupId())
        );

        entity.setDayOfWeek(
                schedule.getDayOfWeek().name()
        );

        entity.setStartTime(
                schedule.getStartTime()
        );

        entity.setEndTime(
                schedule.getEndTime()
        );

        entity.setClassroom(
                schedule.getClassroom()
        );

        return entity;
    }

    public static GroupSchedule toDomain(
            GroupScheduleEntity entity
    ) {
        return new GroupSchedule(
                entity.getGroupScheduleId(),
                entity.getGroupId(),
                WeekDays.valueOf(entity.getDayOfWeek()),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getClassroom()
        );
    }
}
