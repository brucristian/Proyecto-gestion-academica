package org.unisheduler.backend.domain.port.in.academic_programming;

import org.unisheduler.backend.application.service.academic_programming.in.UpdateGroupCommand;
import org.unisheduler.backend.application.service.academic_programming.in.dtos.UpdateGroupResponse;

public interface UpdateGroupUseCase {
    UpdateGroupResponse execute(UpdateGroupCommand command);
}
