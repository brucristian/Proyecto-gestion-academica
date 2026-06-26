package org.unischeduler.ui.service;

import org.unischeduler.backend.application.service.auth.ChangePasswordCommand;
import org.unischeduler.backend.application.service.auth.ChangePasswordResponse;
import org.unischeduler.backend.application.service.auth.login.dtos.UserInfo;
import org.unischeduler.backend.domain.port.in.auth.ChangePasswordUseCase;
import org.unischeduler.ui.app.AppContext;

public class PerfilUiService {
    private final ChangePasswordUseCase changePasswordUseCase;

    public PerfilUiService(ChangePasswordUseCase changePasswordUseCase) {
        this.changePasswordUseCase = AppContext.getChangePasswordService();
    }

    public ChangePasswordResponse changePassword(ChangePasswordCommand command) {
        return changePasswordUseCase.execute(command);
    }

}
