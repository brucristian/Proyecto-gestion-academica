package org.unischeduler.backend.domain.model.auth.vo;

public class EncodedPassword {
    private final String value;

    public EncodedPassword(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password inválido");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
