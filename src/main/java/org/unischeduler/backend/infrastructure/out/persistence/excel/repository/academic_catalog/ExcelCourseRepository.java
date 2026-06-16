package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.CourseEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelCourseRepository {

    private final ExcelDataStore store;

    public ExcelCourseRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public Optional<CourseEntity> findById(String id) {

        return Optional.ofNullable(
                store.getCourses().get(id)
        );
    }

    // =====================================================
    // 🔍 FIND ALL
    // =====================================================
    public List<CourseEntity> findAll() {

        return new ArrayList<>(
                store.getCourses().values()
        );
    }

    // =====================================================
    // 🔍 EXISTS BY CODE
    // =====================================================
    public boolean existsByCode(String code) {

        for (CourseEntity c : store.getCourses().values()) {

            if (code.equals(c.getCode())) {
                return true;
            }
        }

        return false;
    }

    // =====================================================
    // 🔍 FIND BY CODE
    // =====================================================
    public Optional<CourseEntity> findByCode(String code) {

        for (CourseEntity c : store.getCourses().values()) {

            if (code.equals(c.getCode())) {
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

    // =====================================================
    // 💾 SAVE
    // =====================================================
    public CourseEntity save(CourseEntity entity) {

        String id = generateId();
        entity.setCourseId(id);

        store.getCourses().put(id, entity);

        return entity;
    }

    // =====================================================
    // ✏️ UPDATE
    // =====================================================
    public CourseEntity update(CourseEntity entity) {

        if (!store.getCourses().containsKey(entity.getCourseId())) {
            throw new RuntimeException(
                    "No existe una asignatura con id: " + entity.getCourseId()
            );
        }

        store.getCourses().put(entity.getCourseId(), entity);

        return entity;
    }

    // =====================================================
    // ❌ DELETE
    // =====================================================
    public boolean deleteById(String id) {

        return store.getCourses().remove(id) != null;
    }

    // =====================================================
    // 🔢 ID GENERATOR
    // =====================================================
    private String generateId() {
        return String.valueOf(store.getCourses().size() + 1);
    }
}