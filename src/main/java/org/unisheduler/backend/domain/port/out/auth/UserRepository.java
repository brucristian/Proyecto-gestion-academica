package org.unisheduler.backend.domain.port.out.auth;

import org.unisheduler.backend.domain.model.auth.entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String Email);
}
