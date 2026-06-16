package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_history;

import org.unischeduler.backend.infrastructure.out.entity.academic_history.AcademicHistoryEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;

import java.util.Optional;

public class ExcelAcademicHistoryRepository {
    private final ExcelDataStore store;

    public ExcelAcademicHistoryRepository(ExcelDataStore store) {
        this.store = store;
    }

    public Optional<AcademicHistoryEntity> findByStudentId(String studentId) {
        for(AcademicHistoryEntity entity : store.getHistories().values()) {
            if(studentId.equals(entity.getStudentId())) {
                return Optional.of(entity);
            }
        }

        return Optional.empty();
    }

    public AcademicHistoryEntity save(AcademicHistoryEntity entity) {
        String id = generateId();
        entity.setHistoryId(id);

        store.getHistories().put(id, entity);

        return entity;
    }

    // =====================================================
    // 🔢 ID GENERATOR
    // =====================================================
    private String generateId() {
        return String.valueOf(store.getHistories().size() + 1);
    }
}
