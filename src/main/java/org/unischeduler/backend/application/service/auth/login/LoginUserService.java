package org.unischeduler.backend.application.service.auth.login;

import org.unischeduler.backend.application.service.auth.login.dtos.LoginUserResponse;
import org.unischeduler.backend.application.service.auth.login.dtos.UserInfo;
import org.unischeduler.backend.application.service.auth.login.dtos.UserRoleInfo;
import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.backend.domain.model.auth.entity.User;
import org.unischeduler.backend.domain.model.enrollment.entity.Student;
import org.unischeduler.backend.domain.port.in.auth.LoginUserUseCase;
import org.unischeduler.backend.domain.port.out.auth.UserRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.StudentRepository;
import org.unischeduler.backend.domain.port.out.security.PasswordEncoderPort;

import java.util.Optional;

public class LoginUserService implements LoginUserUseCase {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoderPort passwordEncoderPort;

    public LoginUserService(UserRepository userRepository, StudentRepository studentRepository, PasswordEncoderPort passwordEncoderPort) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
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


        Student student = studentRepository.findByUserId(user.getUserId())
                .orElseThrow(()
                        -> new EntityNotFoundException("No existe un estudiante relacionado a el usuario " + user.getUserId()));

        UserInfo userInfo = new UserInfo(
                user.getUserId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail().getValue(),
                new UserRoleInfo(
                        student.getStudentId(),
                        student.getStudentCode()
                ),
                user.getRole().name()
        );

        return new LoginUserResponse(
                true,
                "Inicio de sesion exitoso",
                userInfo
        );
    }
}
