package org.unischeduler.backend.application.service.auth;

import org.unischeduler.backend.domain.model.auth.entity.User;
import org.unischeduler.backend.domain.model.auth.vo.EncodedPassword;
import org.unischeduler.backend.domain.port.in.auth.ChangePasswordUseCase;
import org.unischeduler.backend.domain.port.out.auth.UserRepository;
import org.unischeduler.backend.domain.port.out.security.PasswordEncoderPort;

import java.util.Optional;

public class ChangePasswordService implements ChangePasswordUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoderPort;

    public ChangePasswordService(
            UserRepository userRepository,
            PasswordEncoderPort passwordEncoderPort
    ) {
        this.userRepository = userRepository;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public ChangePasswordResponse execute(ChangePasswordCommand command) {

        Optional<User> optionalUser =
                userRepository.findById(command.getUserId());

        if (optionalUser.isEmpty()) {
            return new ChangePasswordResponse(
                    false,
                    "El usuario no existe."
            );
        }

        User user = optionalUser.get();

        if (!passwordEncoderPort.matches(
                command.getCurrentPassword(),
                user.getPassword().getValue())) {

            return new ChangePasswordResponse(
                    false,
                    "La contraseña actual es incorrecta."
            );
        }

        if (!command.getNewPassword()
                .equals(command.getConfirmPassword())) {

            return new ChangePasswordResponse(
                    false,
                    "La confirmación de la contraseña no coincide."
            );
        }

        if (passwordEncoderPort.matches(
                command.getNewPassword(),
                user.getPassword().getValue())) {

            return new ChangePasswordResponse(
                    false,
                    "La nueva contraseña debe ser diferente a la actual."
            );
        }

        String encodedPassword =
                passwordEncoderPort.encode(command.getNewPassword());

        user.setPassword(
                new EncodedPassword(encodedPassword)
        );

        userRepository.changePassword(user);

        return new ChangePasswordResponse(
                true,
                "Contraseña actualizada correctamente."
        );
    }
}