package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.AcademicPeriodEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelAcademicPeriodRepository {

    private final ExcelDataStore store;

    public ExcelAcademicPeriodRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    //  FIND BY ID
    // =====================================================
    public Optional<AcademicPeriodEntity> findById(String id) {

        return Optional.ofNullable(
                store.getPeriods().get(id)
        );
    }

    // =====================================================
    //  FIND ALL
    // =====================================================
    public List<AcademicPeriodEntity> findAll() {

        return new ArrayList<>(
                store.getPeriods().values()
        );
    }

    // =====================================================
    //  EXISTS BY CODE
    // =====================================================
    public boolean existsByCode(String code) {

        for (AcademicPeriodEntity p : store.getPeriods().values()) {

            if (code.equals(p.getCode())) {
                return true;
            }
        }

        return false;
    }

    // =====================================================
    //  FIND BY CODE
    // =====================================================
    public Optional<AcademicPeriodEntity> findByCode(String code) {

        for (AcademicPeriodEntity p : store.getPeriods().values()) {

            if (code.equals(p.getCode())) {
                return Optional.of(p);
            }
        }

        return Optional.empty();
    }

    // =====================================================
    //  SAVE
    // =====================================================
    public AcademicPeriodEntity save(AcademicPeriodEntity entity) {

        String id = generateId();
        entity.setAcademicPeriodId(id);

        store.getPeriods().put(id, entity);

        return entity;
    }

    // =====================================================
    //  UPDATE
    // =====================================================
    public AcademicPeriodEntity update(AcademicPeriodEntity entity) {

        if (!store.getPeriods().containsKey(entity.getAcademicPeriodId())) {
            throw new RuntimeException(
                    "No existe un periodo académico con id: " + entity.getAcademicPeriodId()
            );
        }

        store.getPeriods().put(entity.getAcademicPeriodId(), entity);

        return entity;
    }

    // =====================================================
    // DELETE
    // =====================================================
    public boolean deleteById(String id) {

        return store.getPeriods().remove(id) != null;
    }

    public Optional<AcademicPeriodEntity> findActive() {
        for (AcademicPeriodEntity p : store.getPeriods().values()) {

            if ("ACTIVE".equals(p.getStatus())) {
                return Optional.of(p);
            }
        }

        return Optional.empty();
    }

    // =====================================================
    //  ID GENERATOR
    // =====================================================
    private String generateId() {
        return String.valueOf(store.getPeriods().size() + 1);
    }
}