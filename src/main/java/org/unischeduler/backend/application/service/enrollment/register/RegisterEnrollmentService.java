package org.unischeduler.backend.application.service.enrollment.register;

import org.unischeduler.backend.application.service.enrollment.register.dtos.RegisterEnrollmentResponse;
import org.unischeduler.backend.application.service.enrollment.register.dtos.RegisterStudentResponse;
import org.unischeduler.backend.application.service.enrollment.validate.ValidateCreditLimitCommand;
import org.unischeduler.backend.application.service.enrollment.validate.ValidatePrerequisiteCommand;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidateCreditLimitResponse;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidatePrerequisiteResponse;
import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicPeriod;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unischeduler.backend.domain.model.academic_programming.entity.Group;
import org.unischeduler.backend.domain.model.enrollment.entity.Enrollment;
import org.unischeduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unischeduler.backend.domain.model.enrollment.entity.Student;
import org.unischeduler.backend.domain.model.enrollment.enums.EnrollmentStatus;
import org.unischeduler.backend.domain.port.in.enrollment.RegisterEnrollmentUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.ValidateCreditLimitUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.ValidatePrerequisiteUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicPeriodRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicProgramRepository;
import org.unischeduler.backend.domain.port.out.academic_programming.GroupRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentDetailRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class RegisterEnrollmentService implements RegisterEnrollmentUseCase {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentDetailRepository enrollmentDetailRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final AcademicProgramRepository academicProgramRepository;
    private final AcademicPeriodRepository academicPeriodRepository;
    private final ValidateCreditLimitUseCase validateCreditLimitUseCase;
    private final ValidatePrerequisiteUseCase validatePrerequisiteUseCase;

    public RegisterEnrollmentService(
            EnrollmentRepository enrollmentRepository,
            EnrollmentDetailRepository enrollmentDetailRepository,
            StudentRepository studentRepository,
            GroupRepository groupRepository,
            AcademicProgramRepository academicProgramRepository,
            AcademicPeriodRepository academicPeriodRepository,
            ValidateCreditLimitUseCase validateCreditLimitUseCase,
            ValidatePrerequisiteUseCase validatePrerequisiteUseCase
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentDetailRepository = enrollmentDetailRepository;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.academicProgramRepository = academicProgramRepository;
        this.academicPeriodRepository = academicPeriodRepository;
        this.validateCreditLimitUseCase = validateCreditLimitUseCase;
        this.validatePrerequisiteUseCase = validatePrerequisiteUseCase;
    }

    @Override
    public RegisterEnrollmentResponse execute(RegisterEnrollmentCommand command) {

        List<Group> groups = command.getGroupIds()
                .stream()
                .map(id -> groupRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "No se encontró el grupo con id " + id
                                )))
                .distinct()
                .toList();

        List<String> courseIds = groups.stream()
                .map(group -> group.getCourse().getCourseId())
                .toList();

        RegisterEnrollmentResponse validationResponse =
                validateData(command.getStudentId(), courseIds);

        if (!validationResponse.isSuccessfully()) {
            return validationResponse;
        }

        Student student = studentRepository.findById(command.getStudentId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "No se encontró el estudiante con id "
                                        + command.getStudentId()));

        AcademicProgram academicProgram =
                academicProgramRepository.findById(
                                student.getAcademicProgram().getAcademicProgramId())
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "No se encontró el programa académico"));

        AcademicPeriod academicPeriod = academicPeriodRepository.findActive()
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "No existe un período académico activo"));

        Enrollment enrollment = getOrCreateEnrollment(
                student,
                academicProgram,
                academicPeriod
        );

        if (hasDuplicatedGroups(enrollment, command.getGroupIds())) {
            return new RegisterEnrollmentResponse(
                    false,
                    "El estudiante ya está matriculado en uno o más grupos seleccionados"
            );
        }

        if (hasDuplicatedCourses(enrollment, groups)) {
            return new RegisterEnrollmentResponse(
                    false,
                    "Ya existe una matrícula para una de las asignaturas seleccionadas"
            );
        }

        for (Group group : groups) {

            if (group.getCapacity() <= 0) {
                return new RegisterEnrollmentResponse(
                        false,
                        "El grupo "
                                + group.getGroupId()
                                + " no tiene cupos disponibles"
                );
            }

            group.setCapacity(group.getCapacity() - 1);
            groupRepository.update(group);
        }

        List<EnrollmentDetail> enrollmentDetails = groups.stream()
                .map(group -> new EnrollmentDetail(
                        null,
                        group,
                        EnrollmentStatus.ENROLLED,
                        0.0
                ))
                .toList();

        enrollmentDetails.forEach(detail ->
                enrollmentDetailRepository.save(
                        detail,
                        enrollment.getEnrollmentId()
                )
        );

        return new RegisterEnrollmentResponse(
                true,
                "Matrícula registrada correctamente"
        );
    }

    private Enrollment getOrCreateEnrollment(
            Student student,
            AcademicProgram academicProgram,
            AcademicPeriod academicPeriod
    ) {

        return enrollmentRepository
                .findByStudentAndActivePeriod(
                        student.getStudentId()
                )
                .orElseGet(() -> {

                    Enrollment newEnrollment = new Enrollment(
                            null,
                            student,
                            academicProgram,
                            LocalDate.now(),
                            null,
                            academicPeriod
                    );

                    return enrollmentRepository.save(newEnrollment);
                });
    }

    private boolean hasDuplicatedGroups(
            Enrollment enrollment,
            List<String> newGroupIds
    ) {

        if (enrollment.getDetails() == null) {
            return false;
        }

        return enrollment.getDetails()
                .stream()
                .map(detail -> detail.getGroup().getGroupId())
                .anyMatch(newGroupIds::contains);
    }

    private boolean hasDuplicatedCourses(
            Enrollment enrollment,
            List<Group> newGroups
    ) {

        if (enrollment.getDetails() == null) {
            return false;
        }

        Set<String> enrolledCourseIds = enrollment.getDetails()
                .stream()
                .map(detail ->
                        detail.getGroup()
                                .getCourse()
                                .getCourseId())
                .collect(Collectors.toSet());

        return newGroups.stream()
                .map(group ->
                        group.getCourse()
                                .getCourseId())
                .anyMatch(enrolledCourseIds::contains);
    }

    public RegisterEnrollmentResponse validateData(
            String studentId,
            List<String> courseIds
    ) {

        ValidateCreditLimitCommand creditLimitCommand =
                new ValidateCreditLimitCommand(
                        studentId,
                        courseIds
                );

        ValidateCreditLimitResponse creditLimitResponse =
                validateCreditLimitUseCase.execute(
                        creditLimitCommand
                );

        if (!creditLimitResponse.isSuccessfully()) {
            return new RegisterEnrollmentResponse(
                    false,
                    "Las asignaturas superan el número de créditos permitido"
            );
        }

        ValidatePrerequisiteCommand prerequisiteCommand =
                new ValidatePrerequisiteCommand(
                        studentId,
                        courseIds
                );

        ValidatePrerequisiteResponse prerequisiteResponse =
                validatePrerequisiteUseCase.execute(
                        prerequisiteCommand
                );

        if (!prerequisiteResponse.isSuccessfully()) {
            return new RegisterEnrollmentResponse(
                    false,
                    "No fue posible matricular debido a cruces de horario"
            );
        }

        return new RegisterEnrollmentResponse(
                true,
                "Validaciones completadas correctamente"
        );
    }
}