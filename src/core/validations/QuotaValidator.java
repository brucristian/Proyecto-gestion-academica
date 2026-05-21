package core.validations;

import module.academic_programming.model.Group;
import shared.exceptions.QuotaUnavailableException;

public class QuotaValidator {

    public void validate(Group group) {

        if (group.getAvailableQuotas() <= 0) {
            throw new QuotaUnavailableException(
                    "El grupo no tiene cupos disponibles"
            );
        }
    }
}