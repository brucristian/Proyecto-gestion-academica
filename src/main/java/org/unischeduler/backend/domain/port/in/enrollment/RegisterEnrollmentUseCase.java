package org.unischeduler.backend.domain.port.in.enrollment;

import org.unischeduler.backend.application.service.enrollment.register.RegisterEnrollmentCommand;
import org.unischeduler.backend.application.service.enrollment.register.dtos.RegisterEnrollmentResponse;
import org.unischeduler.backend.application.service.enrollment.register.dtos.RegisterStudentResponse;

public interface RegisterEnrollmentUseCase {
    RegisterEnrollmentResponse execute(RegisterEnrollmentCommand command);
}
