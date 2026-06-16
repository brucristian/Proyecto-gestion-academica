package org.unischeduler.backend.infrastructure.out.mapper.enrollment;

import org.unischeduler.backend.domain.model.auth.entity.User;
import org.unischeduler.backend.domain.model.enrollment.entity.Student;
import org.unischeduler.backend.infrastructure.out.entity.enrollment.StudentEntity;

public class StudentMapper {

    public static StudentEntity toEntity(Student student) {
        StudentEntity entity = new StudentEntity();

        entity.setStudentId(
                student.getStudentId()
        );

        entity.setStudentCode(
                student.getStudentCode()
        );

        entity.setUserId(
                student.getUser().getUserId()
        );

        return entity;
    }

    public static Student toDomain(
            StudentEntity entity,
            User user
    ) {
        return new Student(
                entity.getStudentId(),
                entity.getStudentCode(),
                user
        );
    }
}