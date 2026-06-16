package org.unischeduler.backend.infrastructure.out.repository.academic_programming;

import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.model.academic_programming.entity.Group;
import org.unischeduler.backend.domain.model.academic_programming.entity.GroupSchedule;
import org.unischeduler.backend.domain.model.academic_programming.entity.Teacher;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.academic_programming.GroupRepository;
import org.unischeduler.backend.domain.port.out.academic_programming.GroupScheduleRepository;
import org.unischeduler.backend.domain.port.out.academic_programming.TeacherRepository;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.GroupEntity;
import org.unischeduler.backend.infrastructure.out.mapper.academic_programming.GroupMapper;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_program.ExcelGroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupRepositoryImpl implements GroupRepository {
    private final ExcelGroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final GroupScheduleRepository groupScheduleRepository;

    public GroupRepositoryImpl(ExcelGroupRepository groupRepository, CourseRepository courseRepository, TeacherRepository teacherRepository, GroupScheduleRepository groupScheduleRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.groupScheduleRepository = groupScheduleRepository;
    }

    @Override
    public Optional<Group> findById(String id) {
        Optional<GroupEntity> entityOptional = groupRepository.findById(id);
        return entityOptional.map(this::toDomain);

    }

    @Override
    public List<Group> findAll() {
        List<GroupEntity> entities = groupRepository.findAll();

        List<Group> groups = new ArrayList<>();
        for(GroupEntity entity : entities) {
            groups.add(toDomain(entity));
        }

        return groups;
    }

    @Override
    public Group save(Group group) {
        GroupEntity entitySaved = groupRepository.save(GroupMapper.toEntity(group));
        return toDomain(entitySaved);
    }

    @Override
    public Group update(Group group) {
        GroupEntity entityUpdated = groupRepository.update(GroupMapper.toEntity(group));
        return toDomain(entityUpdated);
    }

    @Override
    public boolean deleteById(String id) {
        return groupRepository.deleteById(id);
    }

    private Group toDomain(GroupEntity entity) {
        Course course = courseRepository.findById(entity.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el courso con id: " + entity.getCourseId()));
        Teacher teacher = teacherRepository.findById(entity.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el profesor con id: " + entity.getTeacherId()));
        List<GroupSchedule> groupSchedules = groupScheduleRepository.findAllWhereGroupId(entity.getGroupId());

        return GroupMapper.toDomain(entity, course, teacher, groupSchedules);
    }
}
