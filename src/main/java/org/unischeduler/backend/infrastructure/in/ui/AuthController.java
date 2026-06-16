package org.unischeduler.backend.infrastructure.in.ui;

import org.unischeduler.backend.application.service.auth.login.LoginUserCommand;
import org.unischeduler.backend.application.service.auth.login.dtos.LoginUserResponse;
import org.unischeduler.backend.application.service.enrollment.register.RegisterStudentCommand;
import org.unischeduler.backend.application.service.enrollment.register.dtos.RegisterStudentResponse;
import org.unischeduler.backend.domain.port.in.auth.LoginUserUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.RegisterStudentUseCase;
import org.unischeduler.backend.infrastructure.in.ui.dtos.LoginUserRequest;
import org.unischeduler.backend.infrastructure.in.ui.dtos.RegisterStudentRequest;

public class AuthController {
    private final RegisterStudentUseCase registerStudentUseCase;
    private final LoginUserUseCase loginUserUseCase;

    public AuthController(RegisterStudentUseCase registerStudentUseCase, LoginUserUseCase loginUserUseCase) {
        this.registerStudentUseCase = registerStudentUseCase;
        this.loginUserUseCase = loginUserUseCase;
    }

    public RegisterStudentResponse registerNewStudent(RegisterStudentRequest request) {
        RegisterStudentCommand command = new RegisterStudentCommand(
                request.getFirstName(),
                request.getLastName(),
                request.getDocumentType(),
                request.getDocumentNumber(),
                request.getBirthDate(),
                request.getGender(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getEmail(),
                request.getAcademicProgramId(),
                request.getInitialSemester(),
                request.getAdmissionDate(),
                request.getStatus()
        );

        return registerStudentUseCase.execute(command);
    }

    public LoginUserResponse loginUser(LoginUserRequest request) {
        LoginUserCommand command = new LoginUserCommand(
                request.getEmail(),
                request.getPassword()
        );

        return loginUserUseCase.execute(command);
    }
}
