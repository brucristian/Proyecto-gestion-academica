package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_program;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.AcademicProgramEntity;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.SemesterTemplateEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;

import java.io.File;

import java.util.Optional;

public class ExcelSemesterTemplateRepository {

    private final ExcelDataStore store;

    public ExcelSemesterTemplateRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 🔍 FIND BY PROGRAM + SEMESTER
    // =====================================================
    public SemesterTemplateEntity findByProgramAndSemester(AcademicProgramEntity program, int semester) {

        String programId = program.getAcademicProgramId();

        for (SemesterTemplateEntity t : store.getTemplates().values()) {

            if (programId.equals(t.getProgramId()) && t.getSemester() == semester) {
                return t;
            }
        }

        return null;
    }
}