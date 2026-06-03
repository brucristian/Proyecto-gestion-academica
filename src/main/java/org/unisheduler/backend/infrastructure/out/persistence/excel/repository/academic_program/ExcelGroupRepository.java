package org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_program;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unisheduler.backend.infrastructure.out.entity.academic_programming.GroupEntity;
import org.unisheduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelGroupRepository {
    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public Optional<GroupEntity> findById(String id) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table groupTable = doc.getTableByName("Group");

            for (int i = 0; i < groupTable.getRowCount(); i++) {

                String groupId = groupTable.getCellByPosition(0, i).getStringValue();

                if (groupId.equals(id)) {

                    GroupEntity group = new GroupEntity();
                    group.setGroupId(groupId);
                    group.setCourseId(groupTable.getCellByPosition(1, i).getStringValue());
                    group.setTeacherId(groupTable.getCellByPosition(2, i).getStringValue());
                    group.setGroupCode(groupTable.getCellByPosition(3, i).getStringValue());
                    group.setCapacity(Integer.parseInt(groupTable.getCellByPosition(4, i).getStringValue()));

                    return Optional.of(group);
                }
            }

            return Optional.empty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<GroupEntity> findAll() {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table groupTable = doc.getTableByName("Group");

            List<GroupEntity> groups = new ArrayList<>();

            for (int i = 1; i < groupTable.getRowCount(); i++) {

                String groupId = groupTable.getCellByPosition(0, i).getStringValue();

                if (groupId == null || groupId.isBlank()) {
                    continue;
                }

                GroupEntity group = new GroupEntity();
                group.setGroupId(groupId);
                group.setCourseId(groupTable.getCellByPosition(1, i).getStringValue());
                group.setTeacherId(groupTable.getCellByPosition(2, i).getStringValue());
                group.setGroupCode(groupTable.getCellByPosition(3, i).getStringValue());
                group.setCapacity(Integer.parseInt(
                        groupTable.getCellByPosition(4, i).getStringValue()));

                groups.add(group);
            }

            return groups;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteById(String id) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table groupTable = doc.getTableByName("Group");

            for (int i = 1; i < groupTable.getRowCount(); i++) {

                String groupId = groupTable.getCellByPosition(0, i).getStringValue();

                if (groupId.equals(id)) {

                    groupTable.removeRowsByIndex(i, 1);

                    doc.save(new File(FILE_PATH));

                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GroupEntity save(GroupEntity entity) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table groupTable = doc.getTableByName("Group");

            int rowIndex = groupTable.getRowCount();
            String entityId = ExcelIdGenerator.generateNextId(groupTable, 0);

            groupTable.getCellByPosition(0, rowIndex)
                    .setStringValue(entityId);

            groupTable.getCellByPosition(1, rowIndex)
                    .setStringValue(entity.getCourseId());

            groupTable.getCellByPosition(2, rowIndex)
                    .setStringValue(entity.getTeacherId());

            groupTable.getCellByPosition(3, rowIndex)
                    .setStringValue(entity.getGroupCode());

            groupTable.getCellByPosition(4, rowIndex)
                    .setStringValue(String.valueOf(entity.getCapacity()));

            doc.save(new File(FILE_PATH));

            return entity;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GroupEntity update(GroupEntity entity) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table groupTable = doc.getTableByName("Group");

            for (int i = 1; i < groupTable.getRowCount(); i++) {

                String groupId = groupTable.getCellByPosition(0, i).getStringValue();

                if (groupId.equals(entity.getGroupId())) {

                    groupTable.getCellByPosition(1, i)
                            .setStringValue(entity.getCourseId());

                    groupTable.getCellByPosition(2, i)
                            .setStringValue(entity.getTeacherId());

                    groupTable.getCellByPosition(3, i)
                            .setStringValue(entity.getGroupCode());

                    groupTable.getCellByPosition(4, i)
                            .setStringValue(String.valueOf(entity.getCapacity()));

                    doc.save(new File(FILE_PATH));

                    return entity;
                }
            }

            throw new RuntimeException(
                    "Group not found with id: " + entity.getGroupId());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
