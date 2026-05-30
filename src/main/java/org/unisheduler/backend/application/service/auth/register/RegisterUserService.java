package org.unisheduler.backend.application.service.auth.register;

import org.unisheduler.backend.application.service.auth.register.dtos.RegisterUserResponse;
import org.unisheduler.backend.domain.model.auth.entity.User;
import org.unisheduler.backend.domain.model.auth.enums.Role;
import org.unisheduler.backend.domain.model.auth.vo.Password;
import org.unisheduler.backend.domain.port.in.auth.RegisterUserUseCase;
import org.unisheduler.backend.domain.port.out.auth.UserRepository;

public class RegisterUserService implements RegisterUserUseCase {
    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisterUserResponse execute(RegisterUserCommand command) {
        Password password = new Password(command.getPassword());

        User user = new User(
                null,
                command.getFirstName(),
                command.getLastName(),
                password,
                Role.valueOf(command.getRole())
        );

        User userSaved = userRepository.save(user);

        RegisterUserResponse response = new RegisterUserResponse(
                false,
                "Ususario registrado con exito"
        );

        return response;
    }
}
