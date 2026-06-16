package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.auth;

import org.unischeduler.backend.infrastructure.out.entity.auth.RoleEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;

import java.util.Optional;

public class ExcelRoleRepository {

    private final ExcelDataStore dataStore;

    public ExcelRoleRepository(ExcelDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public Optional<RoleEntity> findById(String id) {

        RoleEntity role = dataStore.getRoles().get(id);

        return Optional.ofNullable(role);
    }
}
