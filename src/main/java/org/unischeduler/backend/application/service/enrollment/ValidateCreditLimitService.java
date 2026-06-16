package org.unischeduler.backend.application.service.enrollment;

import org.unischeduler.backend.application.service.enrollment.validate.ValidateCreditLimitCommand;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidateCreditLimitResponse;
import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.model.academic_programming.entity.Group;
import org.unischeduler.backend.domain.model.enrollment.entity.Enrollment;
import org.unischeduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unischeduler.backend.domain.port.in.enrollment.ValidateCreditLimitUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ValidateCreditLimitService
        implements ValidateCreditLimitUseCase {

    private static final int MAX_CREDITS = 19;

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    public ValidateCreditLimitService(
            EnrollmentRepository enrollmentRepository,
            CourseRepository courseRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public ValidateCreditLimitResponse execute(
            ValidateCreditLimitCommand command) {

        int enrolledCredits = enrollmentRepository
                .findByStudentAndActivePeriod(command.getStudentId())
                .map(Enrollment::getDetails)
                .stream()
                .flatMap(List::stream)
                .map(EnrollmentDetail::getGroup)
                .map(Group::getCourse)
                .mapToInt(Course::getCredits)
                .sum();

        int selectedCredits = command.getCourseIds()
                .stream()
                .map(courseId ->
                        courseRepository.findById(courseId)
                                .orElseThrow(() ->
                                        new EntityNotFoundException(
                                                "Course not found: " + courseId
                                        )
                                )
                )
                .mapToInt(Course::getCredits)
                .sum();

        int usedCredits = enrolledCredits + selectedCredits;

        int availableCredits =
                Math.max(0, MAX_CREDITS - usedCredits);

        boolean successfully =
                usedCredits <= MAX_CREDITS;

        List<String> messages = getStrings(successfully, usedCredits, availableCredits);

        return new ValidateCreditLimitResponse(
                successfully,
                messages,
                usedCredits,
                availableCredits
        );
    }

    private static List<String> getStrings(boolean successfully, int usedCredits, int availableCredits) {
        List<String> messages = new ArrayList<>();

        if (!successfully) {
            messages.add(
                    "Se excede el límite máximo de "
                            + MAX_CREDITS
                            + " créditos."
            );
        }

        messages.add(
                "Créditos utilizados: "
                        + usedCredits
                        + "/"
                        + MAX_CREDITS
        );

        messages.add(
                "Créditos disponibles: "
                        + availableCredits
        );
        return messages;
    }
}