package org.unisheduler.backend.domain.model.auth.entity;

import org.unisheduler.backend.domain.model.auth.enums.Role;
import org.unisheduler.backend.domain.model.auth.vo.Password;

public class User {
    private final String userId;
    private final String fullName;
    private final String email;
    private final Password password;
    private final Role role;

    public User(String userId, String fullName, String email, Password password, Role role) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Nombre requerido");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Correo electronico requerido");
        }

        if (password == null) {
            throw new IllegalArgumentException("Contraseña requerida");
        }

        if (role == null) {
            throw  new IllegalArgumentException("Debe tener un rol obligatorio");
        }

        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
