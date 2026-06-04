package org.unisheduler.backend.domain.port.in.academic_programming;

import org.unisheduler.backend.application.service.academic_programming.in.RegisterGroupCommand;
import org.unisheduler.backend.application.service.academic_programming.in.dtos.RegisterGroupResponse;

public interface RegisterGroupUseCase {
    RegisterGroupResponse execute(RegisterGroupCommand command);
}
