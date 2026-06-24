package org.unischeduler.backend.application.service.enrollment.register;

import org.unischeduler.backend.application.service.auth.login.RegisterUserCommand;
import org.unischeduler.backend.application.service.auth.login.dtos.RegisterUserInfo;
import org.unischeduler.backend.application.service.auth.login.dtos.RegisterUserResponse;
import org.unischeduler.backend.application.service.enrollment.register.dtos.RegisterStudentResponse;
import org.unischeduler.backend.application.service.enrollment.register.dtos.StudentInfo;
import org.unischeduler.backend.domain.exceptions.academic_catalog.PeriodActiveNotFoundException;
import org.unischeduler.backend.domain.exceptions.auth.DocumentAlreadyExistsException;
import org.unischeduler.backend.domain.exceptions.auth.EmailAlreadyExistsException;
import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicPeriod;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unischeduler.backend.domain.model.academic_history.entity.AcademicHistory;
import org.unischeduler.backend.domain.model.academic_history.enums.AcademicHistoryCourseStatus;
import org.unischeduler.backend.domain.model.academic_programming.entity.SemesterTemplate;
import org.unischeduler.backend.domain.model.academic_programming.entity.SemesterTemplateDetail;
import org.unischeduler.backend.domain.model.auth.entity.User;
import org.unischeduler.backend.domain.model.auth.enums.DocumentType;
import org.unischeduler.backend.domain.model.auth.enums.Gender;
import org.unischeduler.backend.domain.model.auth.enums.Status;
import org.unischeduler.backend.domain.model.auth.vo.Email;
import org.unischeduler.backend.domain.model.auth.vo.EncodedPassword;
import org.unischeduler.backend.domain.model.enrollment.entity.Enrollment;
import org.unischeduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unischeduler.backend.domain.model.enrollment.entity.Student;
import org.unischeduler.backend.domain.model.enrollment.enums.EnrollmentStatus;
import org.unischeduler.backend.domain.port.in.auth.RegisterUserUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.RegisterEnrollmentUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.RegisterStudentUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicPeriodRepository;
import org.unischeduler.backend.domain.port.out.academic_history.AcademicHistoryRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentDetailRepository;
import org.unischeduler.backend.domain.port.out.security.PasswordEncoderPort;
import org.unischeduler.backend.domain.port.out.security.PasswordGeneratorPort;
import org.unischeduler.backend.domain.port.out.security.StudentCodeGeneratorPort;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicProgramRepository;
import org.unischeduler.backend.domain.port.out.academic_programming.SemesterTemplateRepository;
import org.unischeduler.backend.domain.port.out.auth.UserRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegisterStudentService implements RegisterStudentUseCase {
    private final RegisterUserUseCase registerUserUseCase;
    private final RegisterEnrollmentUseCase registerEnrollmentUseCase;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final AcademicProgramRepository academicProgramRepository;
    private final PasswordGeneratorPort passwordGenerator;
    private final StudentCodeGeneratorPort studentCodeGenerator;
    private final SemesterTemplateRepository semesterTemplateRepository;

    public RegisterStudentService(RegisterUserUseCase registerUserUseCase, RegisterEnrollmentUseCase registerEnrollmentUseCase, UserRepository userRepository, StudentRepository studentRepository, AcademicProgramRepository academicProgramRepository, PasswordGeneratorPort passwordGenerator, StudentCodeGeneratorPort studentCodeGenerator, SemesterTemplateRepository semesterTemplateRepository) {
        this.registerUserUseCase = registerUserUseCase;
        this.registerEnrollmentUseCase = registerEnrollmentUseCase;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.academicProgramRepository = academicProgramRepository;
        this.passwordGenerator = passwordGenerator;
        this.studentCodeGenerator = studentCodeGenerator;
        this.semesterTemplateRepository = semesterTemplateRepository;
    }

    @Override
    public RegisterStudentResponse execute(RegisterStudentCommand command) {

        // Random Password Generation
        String rawPassword = passwordGenerator.generatePassword();
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(
                command.getFirstName(),
                command.getLastName(),
                command.getDocumentType(),
                command.getDocumentNumber(),
                command.getBirthDate(),
                command.getGender(),
                command.getPhoneNumber(),
                command.getAddress(),
                command.getEmail(),
                rawPassword,
                "STUDENT",
                "ACTIVE"
        );

        RegisterUserResponse userResponse = registerUserUseCase.execute(registerUserCommand);

        if(!userResponse.isSuccessfully()) {
            return new RegisterStudentResponse(
                    false,
                    userResponse.getMessage(),
                    null
            );
        }

        Optional<User> userSavedOptional = userRepository.findById(
                userResponse.getUser().getUserId()
        );

        if(userSavedOptional.isEmpty()) {
            return new RegisterStudentResponse(
                    false,
                    "No se encontro el usuario",
                    null
            );
        }

        User userSaved = userSavedOptional.get();


        AcademicProgram academicProgram = academicProgramRepository.findById(command.getAcademicProgramId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro un periodo acadmico con id: " + command.getAcademicProgramId()));

        String studentCode;
        int attempts = 0;
        int maxAttempts = 5;

        do {
            // Random StudentCode Generation
            studentCode = studentCodeGenerator.execute();
            attempts++;

            if(attempts > maxAttempts) {
                throw new IllegalStateException("No se pudo generar un código único para el estudiante");
            }

        } while(studentRepository.existsByStudentCode(studentCode));

        Student student = new Student(
                null,
                studentCode,
                userSaved,
                academicProgram
        );

        Student studentSaved = studentRepository.save(student);

        SemesterTemplate template = semesterTemplateRepository.findByProgramAndSemester(academicProgram, command.getInitialSemester());

        List<String> groupIds = template.getDetails()
                .stream()
                .map(detail -> detail.getGroup().getGroupId())
                .toList();

        RegisterEnrollmentCommand enrollmentCommand = new RegisterEnrollmentCommand(
                studentSaved.getStudentId(),
                groupIds
        );

        registerEnrollmentUseCase.execute(enrollmentCommand);

        return new RegisterStudentResponse(
                true,
                "El estudiante fue registrado con exito",
                new StudentInfo(
                        userSaved.getUserId(),
                        studentSaved.getStudentId(),
                        studentSaved.getStudentCode(),
                        rawPassword
                )
        );
    }
}
