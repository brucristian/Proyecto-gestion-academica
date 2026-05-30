package org.unisheduler.backend.application.service.auth.login;

import org.unisheduler.backend.application.service.auth.login.dtos.LoginUserResponse;
import org.unisheduler.backend.application.service.auth.login.dtos.UserInfo;
import org.unisheduler.backend.domain.model.auth.entity.User;
import org.unisheduler.backend.domain.port.in.auth.LoginUserUseCase;
import org.unisheduler.backend.domain.port.out.auth.UserRepository;

import java.util.Optional;

public class LoginUserService implements LoginUserUseCase {
    private final UserRepository userRepository;

    public LoginUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginUserResponse execute(LoginUserCommand command) {
        Optional<User> userOptional = userRepository.findByEmail(command.getEmail());

        if(userOptional.isEmpty()) {
            return new LoginUserResponse(
                    false,
                    "Usuario no encontrado",
                    null
            );
        }

        User user = userOptional.get();

        UserInfo userInfo = new UserInfo();

        return new LoginUserResponse(
                true,
                "Inicio de sesion exitoso",
                userInfo
        );
    }
}
