package org.unisheduler.backend.infrastructure.out.persistence.excel.repository.enrollment;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unisheduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unisheduler.backend.infrastructure.out.entity.enrollment.EnrollmentDetailEntity;
import org.unisheduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;


import java.io.File;
import java.util.ArrayList;

public class ExcelEnrollmentDetailRepository {
    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public EnrollmentDetailEntity save(EnrollmentDetailEntity enrollmentDetail) {

        try {
            File file = new File(FILE_PATH);
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(file);
            Table table = doc.getTableByName("EnrollmentDetails");

            int newRow = table.getRowCount();
            String detailId = ExcelIdGenerator.generateNextId(table, 0);

            table.getCellByPosition(0, newRow).setStringValue(detailId);
            table.getCellByPosition(1, newRow).setStringValue(enrollmentDetail.getEnrolmentId());
            table.getCellByPosition(2, newRow).setStringValue(enrollmentDetail.getGroupId());
            table.getCellByPosition(3, newRow).setStringValue(enrollmentDetail.getStatus());
            table.getCellByPosition(4, newRow).setDoubleValue(enrollmentDetail.getFinalGrade());

            doc.save(FILE_PATH);

            return enrollmentDetail;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<EnrollmentDetailEntity> findByEnrollmentId(String enrollmentId) {
        ArrayList<EnrollmentDetailEntity> results = new ArrayList<>();

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table table = doc.getTableByName("EnrollmentDetails");

            for (int i = 0; i < table.getRowCount(); i++) {

                String currentEnrollmentId = table.getCellByPosition(1, i).getStringValue();

                if (enrollmentId.equals(currentEnrollmentId)) {

                    EnrollmentDetailEntity entity = new EnrollmentDetailEntity();

                    entity.setEnrollmentDetailId(table.getCellByPosition(0, i).getStringValue());
                    entity.setEnrollmentId(currentEnrollmentId);
                    entity.setGroupId(table.getCellByPosition(2, i).getStringValue());
                    entity.setStatus(table.getCellByPosition(3, i).getStringValue());
                    entity.setFinalGrade(table.getCellByPosition(4, i).getDoubleValue());

                    results.add(entity);
                }
            }

            return results;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
