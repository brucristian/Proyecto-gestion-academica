package org.unisheduler.backend.domain.port.in.auth;

import org.unisheduler.backend.application.service.auth.register.RegisterUserCommand;
import org.unisheduler.backend.application.service.auth.register.dtos.RegisterUserResponse;

public interface RegisterUserUseCase {
    RegisterUserResponse execute(RegisterUserCommand command);
}
