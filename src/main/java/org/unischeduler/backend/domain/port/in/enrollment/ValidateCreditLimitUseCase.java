package org.unischeduler.backend.domain.port.in.enrollment;

import org.unischeduler.backend.application.service.enrollment.validate.ValidateCreditLimitCommand;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidateCreditLimitResponse;

public interface ValidateCreditLimitUseCase {
    ValidateCreditLimitResponse execute(ValidateCreditLimitCommand command);
}
