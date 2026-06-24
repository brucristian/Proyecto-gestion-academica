package org.unischeduler.backend.application.service.academic_catalog.in.prerequisite;

import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.dtos.RegisterPrerequisiteResponse;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Prerequisite;
import org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite.DeletePrerequisiteUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite.RegisterPrerequisiteUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RegisterPrerequisiteService
        implements RegisterPrerequisiteUseCase {

    private final PrerequisiteRepository prerequisiteRepository;
    private final CourseRepository courseRepository;
    private final DeletePrerequisiteUseCase deletePrerequisiteUseCase;

    public RegisterPrerequisiteService(
            PrerequisiteRepository prerequisiteRepository,
            CourseRepository courseRepository,
            DeletePrerequisiteUseCase deletePrerequisiteUseCase
    ) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.courseRepository = courseRepository;
        this.deletePrerequisiteUseCase = deletePrerequisiteUseCase;
    }

    @Override
    public RegisterPrerequisiteResponse execute(RegisterPrerequisiteCommand command) {

        Course course = courseRepository
                .findById(command.getCourseId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El curso no existe."
                        )
                );

        Set<String> uniquePrerequisiteIds =
                new HashSet<>(command.getPrerequisiteIds());

        if (uniquePrerequisiteIds.size()
                != command.getPrerequisiteIds().size()) {

            throw new IllegalArgumentException(
                    "La lista contiene prerrequisitos duplicados."
            );
        }

        for (String prerequisiteId : uniquePrerequisiteIds) {

            if (course.getCourseId().equals(prerequisiteId)) {

                throw new IllegalArgumentException(
                        "Un curso no puede ser prerrequisito de sí mismo."
                );
            }

            courseRepository.findById(prerequisiteId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "El prerrequisito no existe: " + prerequisiteId
                            )
                    );
        }

        // ==========================
        // 🔥 FIX IMPORTANTE AQUÍ
        // ==========================

        List<Prerequisite> existing =
                prerequisiteRepository
                        .findAllPrerequisitesWhereCourseId(course.getCourseId());

        Set<String> existingIds =
                existing.stream()
                        .map(Prerequisite::getPrerequisite)
                        .collect(java.util.stream.Collectors.toSet());

        // solo elimina si realmente hay cambios
        if (!existingIds.equals(uniquePrerequisiteIds)) {

            deletePrerequisiteUseCase.execute(
                    new DeletePrerequisiteCommand(
                            course.getCourseId()
                    )
            );

            for (String prerequisiteId : uniquePrerequisiteIds) {

                prerequisiteRepository.save(
                        course.getCourseId(),
                        prerequisiteId
                );
            }
        }

        return new RegisterPrerequisiteResponse(
                true,
                "Se actualizaron los prerrequisitos con éxito."
        );
    }
}