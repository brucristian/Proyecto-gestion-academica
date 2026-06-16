package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.auth;

import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;
import org.unischeduler.backend.infrastructure.out.entity.auth.UserEntity;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.mapper.auth.RoleMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelUserRepository {

    private final ExcelDataStore store;

    public ExcelUserRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 💾 SAVE
    // =====================================================
    public UserEntity save(UserEntity entity) {

        String id = generateId();
        entity.setUserId(id);

        store.getUsers().put(id, entity);

        return entity;
    }

    // =====================================================
    // 🔍 FIND BY EMAIL
    // =====================================================
    public Optional<UserEntity> findByEmail(String email) {

        for (UserEntity u : store.getUsers().values()) {

            if (email.equals(u.getEmail())) {
                return Optional.of(u);
            }
        }

        return Optional.empty();
    }

    // =====================================================
    // 🔍 EXISTS BY EMAIL
    // =====================================================
    public boolean existsByEmail(String email) {

        for (UserEntity u : store.getUsers().values()) {

            if (email.equals(u.getEmail())) {
                return true;
            }
        }

        return false;
    }

    // =====================================================
    // 🔍 EXISTS BY DOCUMENT NUMBER
    // =====================================================
    public boolean existsByDocumentNumber(String documentNumber) {

        for (UserEntity u : store.getUsers().values()) {

            if (documentNumber.equals(u.getDocumentNumber())) {
                return true;
            }
        }

        return false;
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public Optional<UserEntity> findById(String id) {

        return Optional.ofNullable(
                store.getUsers().get(id)
        );
    }

    // =====================================================
    // 🔍 FIND ALL
    // =====================================================
    public List<UserEntity> findAll() {

        return new ArrayList<>(
                store.getUsers().values()
        );
    }

    // =====================================================
    // ❌ DELETE
    // =====================================================
    public void delete(String id) {
        store.getUsers().remove(id);
    }

    // =====================================================
    // 🔢 ID GENERATOR
    // =====================================================
    private String generateId() {
        return String.valueOf(store.getUsers().size() + 1);
    }
}