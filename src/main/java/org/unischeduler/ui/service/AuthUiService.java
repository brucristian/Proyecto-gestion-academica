package org.unischeduler.ui.service;

import org.unischeduler.backend.application.service.auth.login.LoginUserCommand;
import org.unischeduler.backend.application.service.auth.login.dtos.LoginUserResponse;
import org.unischeduler.backend.domain.port.in.auth.LoginUserUseCase;
import org.unischeduler.ui.app.AppContext;

public class AuthUiService {

    private final LoginUserUseCase loginUserService;

    public AuthUiService() {
        this.loginUserService = AppContext.getLoginUserService();
    }

    public LoginUserResponse login(String email, String password) {

        LoginUserCommand command =
                new LoginUserCommand(email, password);

        return loginUserService.execute(command);
    }
}