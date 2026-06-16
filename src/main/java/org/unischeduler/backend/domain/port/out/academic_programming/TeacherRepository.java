package org.unischeduler.backend.domain.port.out.academic_programming;

import org.unischeduler.backend.domain.model.academic_programming.entity.Teacher;

import java.util.Optional;

public interface TeacherRepository {
    Optional<Teacher> findById(String id);
}
