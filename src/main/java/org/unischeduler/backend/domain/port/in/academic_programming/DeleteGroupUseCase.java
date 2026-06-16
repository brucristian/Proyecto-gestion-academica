package org.unischeduler.backend.domain.port.in.academic_programming;

import org.unischeduler.backend.application.service.academic_programming.in.DeleteGroupCommand;
import org.unischeduler.backend.application.service.academic_programming.in.dtos.DeleteGroupResponse;

public interface DeleteGroupUseCase {
    DeleteGroupResponse execute(DeleteGroupCommand command);
}
