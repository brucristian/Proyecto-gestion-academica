package org.unischeduler.backend.infrastructure.out.security;

import org.unischeduler.backend.domain.port.out.security.StudentCodeGeneratorPort;

import java.security.SecureRandom;

public class StudentCodeGeneratorAdapter implements StudentCodeGeneratorPort {

    @Override
    public String execute() {
        SecureRandom random = new SecureRandom();
        int numero = 10000000 + random.nextInt(90000000);
        return String.valueOf(numero);
    }
}
