package org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite;

import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.DeletePrerequisiteCommand;

public interface DeletePrerequisiteUseCase {
    void execute(DeletePrerequisiteCommand command);
}