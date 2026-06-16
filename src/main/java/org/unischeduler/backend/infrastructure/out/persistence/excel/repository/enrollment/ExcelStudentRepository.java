package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.entity.enrollment.StudentEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;

import java.io.File;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelStudentRepository {

    private final ExcelDataStore store;

    public ExcelStudentRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    //  SAVE
    // =====================================================
    public StudentEntity save(StudentEntity entity) {

        String id = generateId();
        entity.setStudentId(id);

        store.getStudents().put(id, entity);

        return entity;
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public Optional<StudentEntity> findById(String id) {

        return Optional.ofNullable(
                store.getStudents().get(id)
        );
    }

    // =====================================================
    //  FIND ALL
    // =====================================================
    public List<StudentEntity> findAll() {

        return new ArrayList<>(
                store.getStudents().values()
        );
    }

    // =====================================================
    //  EXISTS BY STUDENT CODE
    // =====================================================
    public boolean existsByStudentCode(String code) {

        for (StudentEntity s : store.getStudents().values()) {

            if (code.equals(s.getStudentCode())) {
                return true;
            }
        }

        return false;
    }

    // =====================================================
    //  DELETE
    // =====================================================
    public void delete(String id) {
        store.getStudents().remove(id);
    }

    public Optional<StudentEntity> findStudentByUserId(String userId) {
        for (StudentEntity s : store.getStudents().values()) {
            if (userId.equals(s.getUserId())) {
                return Optional.of(s);
            }
        }

        return Optional.empty();
    }

    // =====================================================
    //  ID GENERATOR
    // =====================================================
    private String generateId() {
        return "STU" + (store.getStudents().size() + 1);
    }
}
