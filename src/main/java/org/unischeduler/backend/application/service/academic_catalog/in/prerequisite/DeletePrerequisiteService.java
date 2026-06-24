package org.unischeduler.backend.application.service.academic_catalog.in.prerequisite;

import org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite.DeletePrerequisiteUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;

public class DeletePrerequisiteService implements DeletePrerequisiteUseCase {

    private final PrerequisiteRepository prerequisiteRepository;
    private final CourseRepository courseRepository;

    public DeletePrerequisiteService(
            PrerequisiteRepository prerequisiteRepository,
            CourseRepository courseRepository
    ) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void execute(DeletePrerequisiteCommand command) {

        courseRepository.findById(
                command.getCourseId()
        ).orElseThrow(() ->
                new IllegalArgumentException(
                        "El curso no existe."
                )
        );

        boolean deleted =
                prerequisiteRepository.deleteWhereCourseId(
                        command.getCourseId()
                );
    }
}