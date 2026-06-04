package org.unisheduler.backend.domain.port.in.academic_programming;

import org.unisheduler.backend.application.service.academic_programming.in.DeleteGroupCommand;
import org.unisheduler.backend.application.service.academic_programming.in.dtos.DeleteGroupResponse;

public interface DeleteGroupUseCase {
    DeleteGroupResponse execute(DeleteGroupCommand command);
}
