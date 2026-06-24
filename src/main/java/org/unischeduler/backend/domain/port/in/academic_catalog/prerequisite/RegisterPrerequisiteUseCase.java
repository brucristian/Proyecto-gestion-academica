package org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite;

import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.RegisterPrerequisiteCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.dtos.RegisterPrerequisiteResponse;

public interface RegisterPrerequisiteUseCase {
    RegisterPrerequisiteResponse execute(RegisterPrerequisiteCommand command);
}
