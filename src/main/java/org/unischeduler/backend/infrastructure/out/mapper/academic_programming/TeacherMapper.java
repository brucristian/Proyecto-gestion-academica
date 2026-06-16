package org.unischeduler.backend.infrastructure.out.mapper.academic_programming;

import org.unischeduler.backend.domain.model.academic_programming.entity.Teacher;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.TeacherEntity;

public class TeacherMapper {

    public static TeacherEntity toEntity(Teacher teacher) {
        TeacherEntity entity = new TeacherEntity();

        entity.setTeacherId(
                teacher.getTeacherId()
        );

        entity.setName(
                teacher.getName()
        );

        return entity;
    }

    public static Teacher toDomain(
            TeacherEntity entity
    ) {
        return new Teacher(
                entity.getTeacherId(),
                entity.getName()
        );
    }
}