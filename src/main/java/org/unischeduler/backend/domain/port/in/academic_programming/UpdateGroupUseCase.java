package org.unischeduler.backend.domain.port.in.academic_programming;

import org.unischeduler.backend.application.service.academic_programming.in.UpdateGroupCommand;
import org.unischeduler.backend.application.service.academic_programming.in.dtos.UpdateGroupResponse;

public interface UpdateGroupUseCase {
    UpdateGroupResponse execute(UpdateGroupCommand command);
}
