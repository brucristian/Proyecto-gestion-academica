package org.unischeduler.backend.domain.port.out.enrollment.repository;

import org.unischeduler.backend.domain.model.enrollment.entity.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);
    Optional<Enrollment> findByStudentAndActivePeriod(String studentId);
    List<Enrollment> findAllWhereStudentId(String studentId);
}
