package org.unischeduler.backend.domain.port.in.auth;

import org.unischeduler.backend.application.service.auth.login.LoginUserCommand;
import org.unischeduler.backend.application.service.auth.login.dtos.LoginUserResponse;

public interface LoginUserUseCase {
    LoginUserResponse execute(LoginUserCommand command);

}
