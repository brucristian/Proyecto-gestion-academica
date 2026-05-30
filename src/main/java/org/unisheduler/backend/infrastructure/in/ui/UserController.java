package org.unisheduler.backend.infrastructure.in.ui;

import org.unisheduler.backend.application.service.auth.register.RegisterUserCommand;
import org.unisheduler.backend.application.service.auth.register.dtos.RegisterUserResponse;
import org.unisheduler.backend.domain.port.in.auth.RegisterUserUseCase;
import org.unisheduler.backend.infrastructure.in.ui.dtos.RegisterUserRequest;

public class UserController {
    private final RegisterUserUseCase registerUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    public RegisterUserResponse registerNewUser(RegisterUserRequest request) {
        RegisterUserCommand command = new RegisterUserCommand(
                request.getFirstName(),
                request.getLastName(),
                request.getPassword(),
                request.getRole()
        );

        return registerUserUseCase.execute(command);
    }
}
