package org.unischeduler.backend.domain.model.auth.vo;

public class Password {
    private final String value;

    public Password(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        if (value.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }

        if (!value.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Debe contener al menos una mayúscula");
        }

        if (!value.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Debe contener al menos una minúscula");
        }

        if (!value.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Debe contener al menos un número");
        }

        if (!value.matches(".*[@#$%^&+=!].*")) {
            throw new IllegalArgumentException("Debe contener al menos un carácter especial");
        }

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
