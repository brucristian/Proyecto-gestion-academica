package org.unischeduler.backend.infrastructure.out.repository.auth;

import org.unischeduler.backend.domain.model.auth.entity.User;
import org.unischeduler.backend.domain.port.out.auth.UserRepository;
import org.unischeduler.backend.infrastructure.out.entity.auth.UserEntity;
import org.unischeduler.backend.infrastructure.out.mapper.auth.UserMapper;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.auth.ExcelUserRepository;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final ExcelUserRepository excelUserRepository;


    public UserRepositoryImpl(ExcelUserRepository excelUserRepository) {
        this.excelUserRepository = excelUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        return UserMapper.toDomain(excelUserRepository.save(entity));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> entityOptional = excelUserRepository.findByEmail(email);

        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }
        UserEntity entity = entityOptional.get();

        return Optional.of(UserMapper.toDomain(entity));
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<UserEntity> entityOptional = excelUserRepository.findById(id);
        if(entityOptional.isEmpty()) {
           return Optional.empty();
        }

        UserEntity entity = entityOptional.get();
        return Optional.of(UserMapper.toDomain(entity));
    }

    @Override
    public boolean existsByEmail(String email) {
        return excelUserRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return excelUserRepository.existsByDocumentNumber(documentNumber);
    }
}
