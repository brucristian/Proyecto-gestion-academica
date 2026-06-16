package org.unischeduler.backend.application.service.enrollment.validate;

import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidatePrerequisiteResponse;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Prerequisite;
import org.unischeduler.backend.domain.model.academic_history.entity.AcademicHistory;
import org.unischeduler.backend.domain.port.in.enrollment.ValidatePrerequisiteUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;
import org.unischeduler.backend.domain.port.out.academic_history.AcademicHistoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidatePrerequisiteService implements ValidatePrerequisiteUseCase {

    private final PrerequisiteRepository prerequisiteRepository;
    private final AcademicHistoryRepository academicHistoryRepository;

    public ValidatePrerequisiteService(
            PrerequisiteRepository prerequisiteRepository,
            AcademicHistoryRepository academicHistoryRepository
    ) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.academicHistoryRepository = academicHistoryRepository;
    }

    @Override
    public ValidatePrerequisiteResponse execute(ValidatePrerequisiteCommand command) {

        Optional<AcademicHistory> historyOptional =
                academicHistoryRepository.findByStudentId(
                        command.getStudentId()
                );

        if (historyOptional.isEmpty()) {

            return new ValidatePrerequisiteResponse(
                    false,
                    List.of("No se encontró historial académico para el estudiante.")
            );
        }

        AcademicHistory history = historyOptional.get();

        Set<String> approvedCourses =
                history.getCompletedCourses()
                        .stream()
                        .map(Course::getCourseId)
                        .collect(Collectors.toSet());

        List<String> messages = new ArrayList<>();

        for (String courseId : command.getCourseIds()) {

            List<Prerequisite> prerequisites =
                    prerequisiteRepository
                            .findAllPrerequisitesWhereCourseId(courseId);

            for (Prerequisite prerequisite : prerequisites) {

                String requiredCourseId =
                        prerequisite.getRequiredCourse().getCourseId();

                if (!approvedCourses.contains(requiredCourseId)) {

                    messages.add(
                            "Para cursar "
                                    + courseId
                                    + " debe haber aprobado previamente "
                                    + prerequisite.getRequiredCourse().getName()
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
