package org.unisheduler.backend.domain.port.in.auth;

import org.unisheduler.backend.application.service.auth.login.LoginUserCommand;
import org.unisheduler.backend.application.service.auth.login.dtos.LoginUserResponse;

public interface LoginUserUseCase {
    LoginUserResponse execute(LoginUserCommand command);

}
