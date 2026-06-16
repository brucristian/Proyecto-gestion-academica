package org.unischeduler.backend.domain.port.in.enrollment;

import org.unischeduler.backend.application.service.enrollment.validate.ValidatePrerequisiteCommand;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidatePrerequisiteResponse;

public interface ValidatePrerequisiteUseCase {
    ValidatePrerequisiteResponse execute(ValidatePrerequisiteCommand command);
}
