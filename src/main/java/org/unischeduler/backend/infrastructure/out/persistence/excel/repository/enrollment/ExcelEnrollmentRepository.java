package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment;

import org.unischeduler.backend.infrastructure.out.entity.enrollment.EnrollmentEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelEnrollmentRepository {

    private final ExcelDataStore store;

    public ExcelEnrollmentRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 💾 SAVE (IN MEMORY)
    // =====================================================
    public EnrollmentEntity save(EnrollmentEntity entity) {

        String id = generateId();
        entity.setEnrollmentId(id);

        store.getEnrollments().put(id, entity);

        return entity;
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public EnrollmentEntity findById(String id) {
        return store.getEnrollments().get(id);
    }

    // =====================================================
    // 🔍 FIND ALL
    // =====================================================
    public List<EnrollmentEntity> findAll() {
        return new ArrayList<>(store.getEnrollments().values());
    }

    // =====================================================
    // ❌ DELETE
    // =====================================================
    public void delete(String id) {
        store.getEnrollments().remove(id);
    }

    public Optional<EnrollmentEntity> findByStudentAndActivePeriod(String studentId, String periodId) {
        for(EnrollmentEntity e : store.getEnrollments().values()) {
            if(studentId.equals(e.getStudentId()) && periodId.equals(e.getAcademicPeriodId())) {
                return Optional.of(e);
            }
        }

        return Optional.empty();
    }

    // =====================================================
    // 🔢 ID GENERATOR
    // =====================================================
    private String generateId() {
        return "ENR" + (store.getEnrollments().size() + 1);
    }
}