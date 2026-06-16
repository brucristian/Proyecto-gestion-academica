package org.unischeduler.backend.infrastructure.out.repository.academic_programming;

import org.unischeduler.backend.domain.model.academic_programming.entity.Teacher;
import org.unischeduler.backend.domain.port.out.academic_programming.TeacherRepository;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.TeacherEntity;
import org.unischeduler.backend.infrastructure.out.mapper.academic_programming.TeacherMapper;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_program.ExcelTeacherRepository;

import java.util.Optional;


public class TeacherRepositoryImpl implements TeacherRepository {
    private final ExcelTeacherRepository teacherRepository;

    public TeacherRepositoryImpl(ExcelTeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Optional<Teacher> findById(String id) {
        Optional<TeacherEntity> entityOptional = teacherRepository.findById(id);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        TeacherEntity entity = entityOptional.get();
        return Optional.of(TeacherMapper.toDomain(entity));
    }
}
