package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_program;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.domain.model.academic_programming.entity.Teacher;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.TeacherEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.Optional;

public class ExcelTeacherRepository {

    private final ExcelDataStore store;

    public ExcelTeacherRepository(ExcelDataStore store) {
        this.store = store;
    }

    public List<TeacherEntity> findAll() {
        return new ArrayList<>(store.getTeachers().values());
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public Optional<TeacherEntity> findById(String id) {

        return Optional.ofNullable(
                store.getTeachers().get(id)
        );
    }
}