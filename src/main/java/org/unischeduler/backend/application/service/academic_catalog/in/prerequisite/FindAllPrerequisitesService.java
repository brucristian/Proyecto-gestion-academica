package org.unischeduler.backend.application.service.academic_catalog.in.prerequisite;

import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.dtos.FindAllPrerequisitesResponse;
import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.PrerequisiteInfo;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Prerequisite;
import org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite.FindAllPrerequisitesUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;

import java.util.List;

public class FindAllPrerequisitesService
        implements FindAllPrerequisitesUseCase {

    private final PrerequisiteRepository prerequisiteRepository;
    private final CourseRepository courseRepository;

    public FindAllPrerequisitesService(
            PrerequisiteRepository prerequisiteRepository,
            CourseRepository courseRepository
    ) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public FindAllPrerequisitesResponse execute(
            FindAllPrerequisitesCommand command
    ) {

        Course course = courseRepository
                .findById(command.getCourseId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El curso no existe."
                        )
                );

        List<PrerequisiteInfo> prerequisites =
                prerequisiteRepository
                        .findAllPrerequisitesWhereCourseId(
                                course.getCourseId()
                        )
                        .stream()
                        .map(PrerequisiteInfo::toInfo)
                        .toList();


        return new FindAllPrerequisitesResponse(
                prerequisites
        );
    }
}
