package org.unischeduler.backend.domain.port.in.enrollment;

import org.unischeduler.backend.application.service.enrollment.register.RegisterStudentCommand;
import org.unischeduler.backend.application.service.enrollment.register.dtos.RegisterStudentResponse;

public interface RegisterStudentUseCase {
    RegisterStudentResponse execute(RegisterStudentCommand command);
}
