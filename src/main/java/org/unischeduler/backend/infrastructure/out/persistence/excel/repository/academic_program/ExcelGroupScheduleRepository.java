package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_program;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.GroupScheduleEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.io.File;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelGroupScheduleRepository {

    private final ExcelDataStore store;

    public ExcelGroupScheduleRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 🔍 FIND BY GROUP ID
    // =====================================================
    public List<GroupScheduleEntity> findAllWhereGroupId(String groupId) {

        List<GroupScheduleEntity> results = new ArrayList<>();

        for (GroupScheduleEntity s : store.getGroupSchedules().values()) {

            if (groupId.equals(s.getGroupId())) {
                results.add(s);
            }
        }

        return results;
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public GroupScheduleEntity findById(String id) {

        return store.getGroupSchedules().get(id);
    }

    // =====================================================
    // 🔍 FIND ALL (OPTIONAL)
    // =====================================================
    public List<GroupScheduleEntity> findAll() {

        return new ArrayList<>(
                store.getGroupSchedules().values()
        );
    }
}