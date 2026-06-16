package org.unischeduler.backend.domain.port.out.academic_history;

import org.unischeduler.backend.domain.model.academic_history.entity.AcademicHistory;

import java.util.Optional;

public interface AcademicHistoryRepository {
    Optional<AcademicHistory> findByStudentId(String studentId);
    AcademicHistory save(AcademicHistory academicHistory);
}
