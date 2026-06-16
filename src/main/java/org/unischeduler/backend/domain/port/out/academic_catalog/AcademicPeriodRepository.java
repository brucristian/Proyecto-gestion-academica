package org.unischeduler.backend.domain.port.out.academic_catalog;


import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicPeriod;

import java.util.List;
import java.util.Optional;

public interface AcademicPeriodRepository {
    Optional<AcademicPeriod> findById(String id);
    List<AcademicPeriod> findAll();
    boolean existsByCode(String code);
    Optional<AcademicPeriod> findByCode(String code);
    AcademicPeriod save(AcademicPeriod academicPeriod);
    AcademicPeriod update(AcademicPeriod academicPeriod);
    boolean deleteById(String id);
    Optional<AcademicPeriod> findActive();
}
