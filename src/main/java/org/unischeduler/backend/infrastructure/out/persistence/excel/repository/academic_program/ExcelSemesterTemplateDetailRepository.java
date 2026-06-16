package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_program;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.SemesterTemplateDetailEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;

import java.util.ArrayList;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

public class ExcelSemesterTemplateDetailRepository {

    private final ExcelDataStore store;

    public ExcelSemesterTemplateDetailRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 🔍 FIND ALL BY TEMPLATE ID
    // =====================================================
    public List<SemesterTemplateDetailEntity> findAllWhereTemplateId(String templateId) {

        List<SemesterTemplateDetailEntity> results = new ArrayList<>();

        for (SemesterTemplateDetailEntity e : store.getTemplateDetails().values()) {

            if (templateId.equals(e.getTemplateId())) {
                results.add(e);
            }
        }

        return results;
    }
}