package org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite;

import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.FindAllPrerequisitesCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.dtos.FindAllPrerequisitesResponse;

public interface FindAllPrerequisitesUseCase {
    FindAllPrerequisitesResponse execute(FindAllPrerequisitesCommand command);
}
