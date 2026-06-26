package org.unischeduler.backend.domain.port.in.academic_programming;

import org.unischeduler.backend.application.service.academic_programming.in.groups.DeleteGroupCommand;
import org.unischeduler.backend.application.service.academic_programming.in.groups.dtos.DeleteGroupResponse;

public interface DeleteGroupUseCase {
    DeleteGroupResponse execute(DeleteGroupCommand command);
}
