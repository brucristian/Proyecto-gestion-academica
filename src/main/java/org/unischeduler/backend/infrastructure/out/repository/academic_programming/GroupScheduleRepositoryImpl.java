package org.unischeduler.backend.infrastructure.out.repository.academic_programming;

import org.unischeduler.backend.domain.model.academic_programming.entity.GroupSchedule;
import org.unischeduler.backend.domain.port.out.academic_programming.GroupScheduleRepository;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.GroupScheduleEntity;
import org.unischeduler.backend.infrastructure.out.mapper.academic_programming.GroupScheduleMapper;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_program.ExcelGroupScheduleRepository;

import java.util.ArrayList;
import java.util.List;

public class GroupScheduleRepositoryImpl implements GroupScheduleRepository {
    private final ExcelGroupScheduleRepository groupScheduleRepository;

    public GroupScheduleRepositoryImpl(ExcelGroupScheduleRepository groupScheduleRepository) {
        this.groupScheduleRepository = groupScheduleRepository;
    }

    @Override
    public List<GroupSchedule> findAllWhereGroupId(String groupId) {
        List<GroupScheduleEntity> entities = groupScheduleRepository.findAllWhereGroupId(groupId);

        List<GroupSchedule> groupSchedules = new ArrayList<>();
        for(GroupScheduleEntity e : entities) {
            GroupSchedule groupSchedule = GroupScheduleMapper.toDomain(e);
            groupSchedules.add(groupSchedule);
        }

        return groupSchedules;
    }
}
