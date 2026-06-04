package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.AcademicPeriodEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelAcademicPeriodRepository {
    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public Optional<AcademicPeriodEntity> findById(String id) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table academicPeriodTable = doc.getTableByName("AcademicPeriod");

            for (int i = 1; i < academicPeriodTable.getRowCount(); i++) {

                String academicPeriodId = academicPeriodTable.getCellByPosition(0, i).getStringValue();

                if (academicPeriodId.equals(id)) {

                    AcademicPeriodEntity academicPeriod = new AcademicPeriodEntity();
                    academicPeriod.setAcademicPeriodId(
                            academicPeriodTable.getCellByPosition(0, i).getStringValue());

                    academicPeriod.setCode(
                            academicPeriodTable.getCellByPosition(1, i).getStringValue());

                    academicPeriod.setName(
                            academicPeriodTable.getCellByPosition(2, i).getStringValue());

                    academicPeriod.setStartDate(
                            academicPeriodTable.getCellByPosition(3, i).getStringValue());

                    academicPeriod.setEndDate(
                            academicPeriodTable.getCellByPosition(4, i).getStringValue());

                    academicPeriod.setStatus(
                            academicPeriodTable.getCellByPosition(5, i).getStringValue());


                    return Optional.of(academicPeriod);
                }
            }

            return Optional.empty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<AcademicPeriodEntity> findAll() {

        List<AcademicPeriodEntity> academicPeriods = new ArrayList<>();

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table academicPeriodTable = doc.getTableByName("AcademicPeriod");

            for (int i = 1; i < academicPeriodTable.getRowCount(); i++) {

                AcademicPeriodEntity academicPeriod = new AcademicPeriodEntity();
                academicPeriod.setAcademicPeriodId(
                        academicPeriodTable.getCellByPosition(0, i).getStringValue());

                academicPeriod.setCode(
                        academicPeriodTable.getCellByPosition(1, i).getStringValue());

                academicPeriod.setName(
                        academicPeriodTable.getCellByPosition(2, i).getStringValue());

                academicPeriod.setStartDate(
                        academicPeriodTable.getCellByPosition(3, i).getStringValue());

                academicPeriod.setEndDate(
                        academicPeriodTable.getCellByPosition(4, i).getStringValue());

                academicPeriod.setStatus(
                        academicPeriodTable.getCellByPosition(5, i).getStringValue());


                academicPeriods.add(academicPeriod);
            }

            return academicPeriods;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsByCode(String code) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table academicPeriodTable = doc.getTableByName("AcademicPeriod");

            for (int i = 1; i < academicPeriodTable.getRowCount(); i++) {

                String currentCode =
                        academicPeriodTable.getCellByPosition(1, i).getStringValue();

                if (code.equals(currentCode)) {
                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<AcademicPeriodEntity> findByCode(String code) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table academicPeriodTable = doc.getTableByName("AcademicPeriod");

            for (int i = 1; i < academicPeriodTable.getRowCount(); i++) {

                String academicPeriodCode = academicPeriodTable.getCellByPosition(1, i).getStringValue();

                if (academicPeriodCode.equals(code)) {

                    AcademicPeriodEntity entity = new AcademicPeriodEntity();

                    entity.setAcademicPeriodId(
                            academicPeriodTable.getCellByPosition(0, i).getStringValue());

                    entity.setCode(
                            academicPeriodTable.getCellByPosition(1, i).getStringValue());

                    entity.setName(
                            academicPeriodTable.getCellByPosition(2, i).getStringValue());

                    entity.setStartDate(
                            academicPeriodTable.getCellByPosition(3, i).getStringValue());

                    entity.setEndDate(
                            academicPeriodTable.getCellByPosition(4, i).getStringValue());

                    entity.setStatus(
                            academicPeriodTable.getCellByPosition(5, i).getStringValue());

                    return Optional.of(entity);
                }
            }

            return Optional.empty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AcademicPeriodEntity save(AcademicPeriodEntity entity) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));

            Table academicPeriodTable = doc.getTableByName("AcademicPeriod");

            String newAcademicPeriodId = ExcelIdGenerator.generateNextId(academicPeriodTable, 0);
            entity.setAcademicPeriodId(newAcademicPeriodId);

            int academicPeriodRow = academicPeriodTable.getRowCount();
            academicPeriodTable.appendRow();

            academicPeriodTable.getCellByPosition(0, academicPeriodRow)
                    .setStringValue(entity.getAcademicPeriodId());

            academicPeriodTable.getCellByPosition(1, academicPeriodRow)
                    .setStringValue(entity.getCode());

            academicPeriodTable.getCellByPosition(2, academicPeriodRow)
                    .setStringValue(entity.getName());

            academicPeriodTable.getCellByPosition(3, academicPeriodRow)
                    .setStringValue(entity.getStartDate());

            academicPeriodTable.getCellByPosition(4, academicPeriodRow)
                    .setStringValue(entity.getEndDate());

            academicPeriodTable.getCellByPosition(5, academicPeriodRow)
                    .setStringValue(entity.getStatus());


            doc.save(FILE_PATH);

            return entity;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AcademicPeriodEntity update(AcademicPeriodEntity entity) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));

            Table academicPeriodTable = doc.getTableByName("AcademicPeriod");

            for (int i = 1; i < academicPeriodTable.getRowCount(); i++) {

                String currentId = academicPeriodTable.getCellByPosition(0, i).getStringValue();

                if (entity.getAcademicPeriodId().equals(currentId)) {

                    academicPeriodTable.getCellByPosition(0, i)
                            .setStringValue(entity.getAcademicPeriodId());

                    academicPeriodTable.getCellByPosition(1, i)
                            .setStringValue(entity.getCode());

                    academicPeriodTable.getCellByPosition(2, i)
                            .setStringValue(entity.getName());

                    academicPeriodTable.getCellByPosition(3, i)
                            .setStringValue(entity.getStartDate());

                    academicPeriodTable.getCellByPosition(4, i)
                            .setStringValue(entity.getEndDate());

                    academicPeriodTable.getCellByPosition(5, i)
                            .setStringValue(entity.getStatus());


                    doc.save(FILE_PATH);

                    return entity;
                }
            }

            throw new RuntimeException(
                    "No existe una periodo academico con id: " + entity.getAcademicPeriodId()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteById(String id) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));

            Table academicPeriodTable = doc.getTableByName("AcademicPeriod");

            for (int i = 1; i < academicPeriodTable.getRowCount(); i++) {

                String currentId = academicPeriodTable.getCellByPosition(0, i).getStringValue();

                if (id.equals(currentId)) {

                    academicPeriodTable.removeRowsByIndex(i, 1);

                    doc.save(FILE_PATH);

                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
