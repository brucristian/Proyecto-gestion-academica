package org.unisheduler.backend.infrastructure.out.repository;

import org.unisheduler.backend.domain.model.auth.entity.User;
import org.unisheduler.backend.domain.port.out.auth.UserRepository;
import org.unisheduler.backend.infrastructure.out.entity.UserEntity;
import org.unisheduler.backend.infrastructure.out.mapper.UserMapper;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.ExcelUserRepository;

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
    public Optional<User> findByEmail(String Email) {
        return Optional.empty();
    }
}
