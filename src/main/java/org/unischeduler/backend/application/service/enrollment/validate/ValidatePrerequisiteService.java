package org.unischeduler.backend.application.service.enrollment.validate;

import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidatePrerequisiteResponse;
import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Prerequisite;
import org.unischeduler.backend.domain.model.academic_history.entity.AcademicHistory;
import org.unischeduler.backend.domain.model.enrollment.enums.EnrollmentStatus;
import org.unischeduler.backend.domain.port.in.enrollment.ValidatePrerequisiteUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;
import org.unischeduler.backend.domain.port.out.academic_history.AcademicHistoryRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentDetailRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidatePrerequisiteService
        implements ValidatePrerequisiteUseCase {

    private final PrerequisiteRepository prerequisiteRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentDetailRepository enrollmentDetailRepository;
    private final CourseRepository courseRepository;

    public ValidatePrerequisiteService(
            PrerequisiteRepository prerequisiteRepository,
            EnrollmentRepository enrollmentRepository,
            EnrollmentDetailRepository enrollmentDetailRepository,
            CourseRepository courseRepository
    ) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentDetailRepository = enrollmentDetailRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public ValidatePrerequisiteResponse execute(
            ValidatePrerequisiteCommand command
    ) {

        Set<String> approvedCourses = enrollmentRepository
                .findAllWhereStudentId(command.getStudentId())
                .stream()
                .flatMap(enrollment ->
                        enrollmentDetailRepository
                                .findByEnrollmentId(
                                        enrollment.getEnrollmentId()
                                )
                                .stream())
                .filter(detail ->
                        detail.getStatus() == EnrollmentStatus.COMPLETED)
                .map(detail ->
                        detail.getGroup()
                                .getCourse()
                                .getCourseId())
                .collect(Collectors.toSet());

        List<String> messages = new ArrayList<>();

        List<Course> requestedCourses = command.getCourseIds()
                .stream()
                .map(id -> courseRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "No se encontró una asignatura con id "
                                                + id)))
                .toList();

        // 1. Verificar que la materia no haya sido aprobada anteriormente
        for (Course course : requestedCourses) {

            if (approvedCourses.contains(course.getCourseId())) {

                messages.add(
                        "La asignatura "
                                + course.getName()
                                + " ya fue aprobada anteriormente"
                );
            }
        }

        // 2. Verificar prerrequisitos
        for (Course course : requestedCourses) {

            List<Prerequisite> prerequisites =
                    prerequisiteRepository
                            .findAllPrerequisitesWhereCourseId(
                                    course.getCourseId()
                            );

            for (Prerequisite prerequisite : prerequisites) {

                String requiredCourseId =
                        prerequisite.getRequiredCourse()
                                .getCourseId();

                if (!approvedCourses.contains(requiredCourseId)) {

                    messages.add(
                            "Para cursar "
                                    + course.getName()
                                    + " debe haber aprobado previamente "
                                    + prerequisite
                                    .getRequiredCourse()
                                    .getName()
                    );
                }
            }
        }

        return new ValidatePrerequisiteResponse(
                messages.isEmpty(),
                messages
        );
    }
}