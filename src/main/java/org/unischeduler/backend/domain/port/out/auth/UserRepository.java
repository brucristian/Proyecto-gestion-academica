package org.unischeduler.backend.domain.port.out.auth;

import org.unischeduler.backend.domain.model.auth.entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);
    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);
}
