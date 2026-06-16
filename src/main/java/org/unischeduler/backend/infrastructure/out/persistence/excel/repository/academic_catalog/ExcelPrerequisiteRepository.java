package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.PrerequisiteEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class ExcelPrerequisiteRepository {

    private final ExcelDataStore store;

    public ExcelPrerequisiteRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 🔍 FIND ALL BY COURSE ID
    // =====================================================
    public List<PrerequisiteEntity> findAllPrerequisitesWhereCourseId(String courseId) {

        List<PrerequisiteEntity> result = new ArrayList<>();

        for (PrerequisiteEntity p : store.getPrerequisites().values()) {

            if (courseId.equals(p.getCourseId())) {
                result.add(p);
            }
        }

        return result;
    }

    // =====================================================
    // 💾 SAVE RELATION
    // =====================================================
    public PrerequisiteEntity save(String courseId, String coursePrerequisiteId) {

        String id = generateId();

        PrerequisiteEntity entity = new PrerequisiteEntity();
        entity.setPrerequisiteId(id);
        entity.setCourseId(courseId);
        entity.setPrerequisiteCourseId(coursePrerequisiteId);

        store.getPrerequisites().put(id, entity);

        return entity;
    }

    // =====================================================
    // ❌ DELETE BY COURSE OR PREREQUISITE COURSE ID
    // =====================================================
    public boolean deleteWhereCourseId(String id) {

        boolean removed = false;

        List<String> toRemove = new ArrayList<>();

        for (PrerequisiteEntity p : store.getPrerequisites().values()) {

            if (id.equals(p.getCourseId()) ||
                    id.equals(p.getPrerequisiteCourseId())) {

                toRemove.add(p.getPrerequisiteId());
            }
        }

        for (String removeId : toRemove) {
            store.getPrerequisites().remove(removeId);
            removed = true;
        }

        return removed;
    }

    // =====================================================
    // 🔢 ID GENERATOR
    // =====================================================
    private String generateId() {
        return "PRE" + (store.getPrerequisites().size() + 1);
    }
}