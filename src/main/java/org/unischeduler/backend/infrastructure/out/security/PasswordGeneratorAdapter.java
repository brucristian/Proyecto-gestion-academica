package org.unischeduler.backend.infrastructure.out.security;

import org.unischeduler.backend.domain.port.out.security.PasswordGeneratorPort;

import java.security.SecureRandom;

public class PasswordGeneratorAdapter implements PasswordGeneratorPort {
    private final String CHARACTERES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789" +
            "!@#$%&*";

    @Override
    public String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for(int i = 0; i < 8; i++) {
            int indice = random.nextInt(CHARACTERES.length());
            password.append(CHARACTERES.charAt(indice));
        }

        return password.toString();
    }
}
