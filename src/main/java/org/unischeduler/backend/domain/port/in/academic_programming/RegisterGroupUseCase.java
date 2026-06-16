package org.unischeduler.backend.domain.port.in.academic_programming;

import org.unischeduler.backend.application.service.academic_programming.in.RegisterGroupCommand;
import org.unischeduler.backend.application.service.academic_programming.in.dtos.RegisterGroupResponse;

public interface RegisterGroupUseCase {
    RegisterGroupResponse execute(RegisterGroupCommand command);
}
