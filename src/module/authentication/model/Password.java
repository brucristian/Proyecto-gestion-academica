package module.authentication.model;

import shared.exceptions.InvalidPasswordLengthException;
import shared.exceptions.PasswordRequiredException;

public class Password {

    //================// Atributos //================//
    private final String value;

    //================// Constructores //================//

    public Password(String value) {
        if(value.isBlank() || value.isEmpty()) {
            throw new PasswordRequiredException("La contraseña es obligatoria");
        }

        if (value.length() < 8) {
            throw new InvalidPasswordLengthException("La contraseña debe tener al menos 8 caracteres");
        }
        this.value = value;
    }


    //================// Getters //================//

    public String getValue() {
        return value;
    }
}
