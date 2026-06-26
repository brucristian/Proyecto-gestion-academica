package org.unischeduler.backend.domain.port.in.auth;

import org.unischeduler.backend.application.service.auth.ChangePasswordCommand;
import org.unischeduler.backend.application.service.auth.ChangePasswordResponse;

public interface ChangePasswordUseCase {
    ChangePasswordResponse execute(ChangePasswordCommand command);
}
