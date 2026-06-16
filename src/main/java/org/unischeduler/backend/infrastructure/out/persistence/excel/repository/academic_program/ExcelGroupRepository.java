package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_program;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.GroupEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelGroupRepository {

    private final ExcelDataStore store;

    public ExcelGroupRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 💾 SAVE
    // =====================================================
    public GroupEntity save(GroupEntity entity) {

        String id = generateId();
        entity.setGroupId(id);

        store.getGroups().put(id, entity);

        return entity;
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public Optional<GroupEntity> findById(String id) {

        return Optional.ofNullable(
                store.getGroups().get(id)
        );
    }

    // =====================================================
    // 🔍 FIND ALL
    // =====================================================
    public List<GroupEntity> findAll() {

        return new ArrayList<>(
                store.getGroups().values()
        );
    }

    // =====================================================
    // ❌ DELETE BY ID
    // =====================================================
    public boolean deleteById(String id) {

        return store.getGroups().remove(id) != null;
    }

    // =====================================================
    // ✏️ UPDATE
    // =====================================================
    public GroupEntity update(GroupEntity entity) {

        if (!store.getGroups().containsKey(entity.getGroupId())) {
            throw new RuntimeException(
                    "Group not found with id: " + entity.getGroupId()
            );
        }

        store.getGroups().put(entity.getGroupId(), entity);

        return entity;
    }

    // =====================================================
    // 🔢 ID GENERATOR
    // =====================================================
    private String generateId() {
        return "GRP" + (store.getGroups().size() + 1);
    }
}
