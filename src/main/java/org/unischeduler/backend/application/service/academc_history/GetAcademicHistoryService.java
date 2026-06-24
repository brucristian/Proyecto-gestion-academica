package org.unischeduler.backend.application.service.academc_history;

import org.unischeduler.backend.domain.model.enrollment.entity.Enrollment;
import org.unischeduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unischeduler.backend.domain.model.enrollment.enums.EnrollmentStatus;
import org.unischeduler.backend.domain.port.in.academic_history.GetAcademicHistoryUseCase;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;

import java.util.List;
import java.util.Optional;

public class GetAcademicHistoryService implements GetAcademicHistoryUseCase {
    private final EnrollmentRepository enrollmentRepository;

    public GetAcademicHistoryService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public GetAcademicHistoryResponse execute(GetAcademicHistoryCommand command) {

        List<Enrollment> enrollments =
                enrollmentRepository.findAllWhereStudentId(command.getStudentId());

        if (enrollments.isEmpty()) {
            return new GetAcademicHistoryResponse(
                    false,
                    "No se encontró historial académico",
                    "",
                    0,
                    0.0,
                    List.of()
            );
        }

        List<AcademicHistoryInfo> history = enrollments.stream()
                .flatMap(enrollment ->
                        enrollment.getDetails().stream()
                                .map(detail ->
                                        AcademicHistoryInfo.fromEnrollmentDetail(
                                                detail,
                                                enrollment.getAcademicPeriod().getCode()
                                        )
                                )
                )
                .toList();

        String programName = enrollments.getFirst()
                .getAcademicProgram()
                .getName();

        int approvedCredits = enrollments.stream()
                .flatMap(enrollment -> enrollment.getDetails().stream())
                .filter(detail -> detail.getStatus() == EnrollmentStatus.COMPLETED)
                .mapToInt(detail -> detail.getGroup().getCourse().getCredits())
                .sum();

        double average = enrollments.stream()
                .flatMap(enrollment -> enrollment.getDetails().stream())
                .mapToDouble(EnrollmentDetail::getFinalGrade)
                .average()
                .orElse(0.0);

        return new GetAcademicHistoryResponse(
                true,
                "Historial académico obtenido correctamente",
                programName,
                approvedCredits,
                average,
                history
        );
    }
}
