package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment;

import org.unischeduler.backend.infrastructure.out.entity.enrollment.EnrollmentDetailEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;

import java.util.ArrayList;

import java.util.List;

public class ExcelEnrollmentDetailRepository {

    private final ExcelDataStore store;

    public ExcelEnrollmentDetailRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 💾 SAVE (IN MEMORY)
    // =====================================================
    public EnrollmentDetailEntity save(EnrollmentDetailEntity detail) {

        String id = generateId();
        detail.setEnrollmentDetailId(id);

        store.getEnrollmentDetails().put(id, detail);

        return detail;
    }

    // =====================================================
    // 🔍 FIND BY ENROLLMENT ID (LINEAR SEARCH)
    // =====================================================
    public List<EnrollmentDetailEntity> findByEnrollmentId(String enrollmentId) {

        List<EnrollmentDetailEntity> results = new ArrayList<>();

        for (EnrollmentDetailEntity detail : store.getEnrollmentDetails().values()) {

            if (detail.getEnrollmentId() != null &&
                    detail.getEnrollmentId().equals(enrollmentId)) {

                results.add(detail);
            }
        }

        return results;
    }

    // =====================================================
    // ⚙️ DELETE (OPTIONAL BUT CONSISTENT)
    // =====================================================
    public void delete(String enrollmentDetailId) {
        store.getEnrollmentDetails().remove(enrollmentDetailId);
    }

    // =====================================================
    // 🧠 FIND BY ID
    // =====================================================
    public EnrollmentDetailEntity findById(String id) {
        return store.getEnrollmentDetails().get(id);
    }

    // =====================================================
    // 🔢 ID GENERATOR
    // =====================================================
    private String generateId() {
        return "ED" + (store.getEnrollmentDetails().size() + 1);
    }
}