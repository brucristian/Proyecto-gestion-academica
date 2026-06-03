package org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_program;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unisheduler.backend.domain.model.academic_programming.entity.GroupSchedule;
import org.unisheduler.backend.infrastructure.out.entity.academic_programming.GroupScheduleEntity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExcelGroupScheduleRepository {

    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public List<GroupScheduleEntity> findAllWhereGroupId(String groupId) {

        List<GroupScheduleEntity> results = new ArrayList<>();

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table table = doc.getTableByName("GroupSchedule");

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

            for (int i = 0; i < table.getRowCount(); i++) {

                String currentGroupId = table.getCellByPosition(1, i).getStringValue();

                if (groupId.equals(currentGroupId)) {

                    GroupScheduleEntity entity = new GroupScheduleEntity();

                    entity.setGroupScheduleId(table.getCellByPosition(0, i).getStringValue());
                    entity.setGroupId(currentGroupId);
                    entity.setDayOfWeek(table.getCellByPosition(2, i).getStringValue());

                    String startStr = table.getCellByPosition(3, i).getStringValue();
                    String endStr = table.getCellByPosition(4, i).getStringValue();

                    entity.setStartTime(LocalTime.parse(startStr, timeFormatter));
                    entity.setEndTime(LocalTime.parse(endStr, timeFormatter));

                    entity.setClassroom(table.getCellByPosition(5, i).getStringValue());

                    results.add(entity);
                }
            }

            return results;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GroupScheduleEntity findById(String id) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table table = doc.getTableByName("GroupSchedule");

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

            for (int i = 0; i < table.getRowCount(); i++) {

                String groupScheduleId = table.getCellByPosition(0, i).getStringValue();

                if (id.equals(groupScheduleId)) {

                    GroupScheduleEntity entity = new GroupScheduleEntity();

                    entity.setGroupScheduleId(groupScheduleId);
                    entity.setGroupId(table.getCellByPosition(1, i).getStringValue());
                    entity.setDayOfWeek(table.getCellByPosition(2, i).getStringValue());

                    String startStr = table.getCellByPosition(3, i).getStringValue();
                    String endStr = table.getCellByPosition(4, i).getStringValue();

                    entity.setStartTime(LocalTime.parse(startStr, timeFormatter));
                    entity.setEndTime(LocalTime.parse(endStr, timeFormatter));

                    entity.setClassroom(table.getCellByPosition(5, i).getStringValue());

                    return entity;
                }
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}