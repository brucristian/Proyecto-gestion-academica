package org.unisheduler.backend.application.service.auth.login;

import org.unisheduler.backend.application.service.auth.login.dtos.LoginUserResponse;
import org.unisheduler.backend.application.service.auth.login.dtos.UserInfo;
import org.unisheduler.backend.domain.model.auth.entity.User;
import org.unisheduler.backend.domain.port.in.auth.LoginUserUseCase;
import org.unisheduler.backend.domain.port.out.auth.UserRepository;
import org.unisheduler.backend.domain.port.out.security.PasswordEncoderPort;

import java.util.Optional;

public class LoginUserService implements LoginUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoderPort;

    public LoginUserService(UserRepository userRepository, PasswordEncoderPort passwordEncoderPort) {
        this.userRepository = userRepository;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public LoginUserResponse execute(LoginUserCommand command) {
        Optional<User> userOptional = userRepository.findByEmail(command.getEmail());

        if(userOptional.isEmpty()) {
            System.out.println("Case 1");
            return new LoginUserResponse(
                    false,
                    "Usuario o contraseña incorrectos",
                    null
            );
        }

        User user = userOptional.get();

        if(!passwordEncoderPort.matches(command.getPassword(), user.getPassword().getValue())) {
            System.out.println("Case 2");
            return new LoginUserResponse(
                    false,
                    "Usuario o contraseña incorrectos",
                    null
            );
        }

        UserInfo userInfo = new UserInfo(
                user.getUserId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail().getValue(),
                user.getRole().name()
        );

        return new LoginUserResponse(
                true,
                "Inicio de sesion exitoso",
                userInfo
        );
    }
}
