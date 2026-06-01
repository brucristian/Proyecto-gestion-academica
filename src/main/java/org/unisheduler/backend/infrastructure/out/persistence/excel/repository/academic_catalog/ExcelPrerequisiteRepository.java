package org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unisheduler.backend.infrastructure.out.entity.academic_catalog.PrerequisiteEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExcelPrerequisiteRepository {
    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public List<PrerequisiteEntity> findAllPrerequisitesWhereCourseId(String courseId) {

        List<PrerequisiteEntity> prerequisites = new ArrayList<>();

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));

            Table table = doc.getTableByName("Prerequisite");

            if (table == null) {
                throw new RuntimeException("No existe la hoja 'Prerequisite' en el archivo ODS");
            }

            for (int i = 1; i < table.getRowCount(); i++) {

                String currentCourseId = table.getCellByPosition(1, i).getStringValue();

                if (courseId.equals(currentCourseId)) {

                    PrerequisiteEntity entity = new PrerequisiteEntity();

                    entity.setPrerequisiteId(
                            table.getCellByPosition(0, i).getStringValue()
                    );

                    entity.setCourseId(currentCourseId);

                    entity.setPrerequisiteCourseId(
                            table.getCellByPosition(2, i).getStringValue()
                    );

                    prerequisites.add(entity);
                }
            }

            return prerequisites;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
