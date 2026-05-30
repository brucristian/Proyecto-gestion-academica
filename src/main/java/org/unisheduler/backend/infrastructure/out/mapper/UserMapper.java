package org.unisheduler.backend.infrastructure.out.mapper;

import org.unisheduler.backend.domain.model.auth.entity.User;
import org.unisheduler.backend.domain.model.auth.enums.Role;
import org.unisheduler.backend.domain.model.auth.vo.Password;
import org.unisheduler.backend.infrastructure.out.entity.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();

        entity.setUserId(user.getUserId());
        entity.setFullName(user.getFullName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword().getValue());
        entity.setRole(user.getRole().name());

        return entity;
    }

    public static User toDomain(UserEntity userEntity) {
        Password password = new Password(userEntity.getPassword());

        return new User(
                userEntity.getUserId(),
                userEntity.getFullName(),
                userEntity.getEmail(),
                password,
                Role.valueOf(userEntity.getRole())
        );
    }
}
