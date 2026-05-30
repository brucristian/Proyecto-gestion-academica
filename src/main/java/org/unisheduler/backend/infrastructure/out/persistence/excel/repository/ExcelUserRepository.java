package org.unisheduler.backend.infrastructure.out.persistence.excel.repository;

import org.unisheduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;
import org.unisheduler.backend.infrastructure.out.entity.UserEntity;

import java.io.File;
import java.util.Optional;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unisheduler.backend.infrastructure.out.mapper.RoleMapper;

public class ExcelUserRepository{

    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public UserEntity save(UserEntity entity) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));

            Table userTable = doc.getTableByName("User");
            Table userRoleTable = doc.getTableByName("UserRole");

            String newUserId = ExcelIdGenerator.generateNextId(userTable, 0);
            entity.setUserId(newUserId);

            int userRow = userTable.getRowCount();
            userTable.appendRow();

            userTable.getCellByPosition(0, userRow).setStringValue(entity.getUserId());
            userTable.getCellByPosition(1, userRow).setStringValue(entity.getFullName());
            userTable.getCellByPosition(2, userRow).setStringValue(entity.getEmail());
            userTable.getCellByPosition(3, userRow).setStringValue(entity.getPassword());

            String roleId = RoleMapper.toId(entity.getRole());
            String userRoleId = ExcelIdGenerator.generateNextId(userRoleTable, 0);

            int roleRow = userRoleTable.getRowCount();
            userRoleTable.appendRow();

            userRoleTable.getCellByPosition(0, roleRow).setStringValue(userRoleId);
            userRoleTable.getCellByPosition(1, roleRow).setStringValue(newUserId);
            userRoleTable.getCellByPosition(2, roleRow).setStringValue(roleId);

            doc.save(FILE_PATH);

            return entity;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserEntity> findByEmail(String Email) {
        return Optional.empty();
    }
}