package org.unischeduler.backend.application.service.academic_catalog.in.course;



import org.unischeduler.backend.application.service.academic_catalog.in.course.dtos.RegisterCourseResponse;
import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.CourseInfo;
import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.PrerequisiteInfo;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.RegisterCourseUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegisterCourseService implements RegisterCourseUseCase {

    private final CourseRepository courseRepository;
    private final PrerequisiteRepository prerequisiteRepository;

    public RegisterCourseService(
            CourseRepository courseRepository,
            PrerequisiteRepository prerequisiteRepository
    ) {
        this.courseRepository = courseRepository;
        this.prerequisiteRepository = prerequisiteRepository;
    }

    @Override
    public RegisterCourseResponse execute(RegisterCourseCommand command) {

        if (courseRepository.existsByCode(command.getCourseCode())) {
            return new RegisterCourseResponse(
                    false,
                    "El código de la asignatura ya está en uso",
                    null
            );
        }

        List<Course> prerequisites = new ArrayList<>();
        List<PrerequisiteInfo> prerequisiteInfos = new ArrayList<>();

        for (String prerequisiteId : command.getPrerequisiteIds()) {

            Course prerequisite = courseRepository
                    .findById(prerequisiteId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "El prerrequisito no existe: " + prerequisiteId
                            )
                    );

            prerequisites.add(prerequisite);

            prerequisiteInfos.add(
                    new PrerequisiteInfo(
                            prerequisite.getCourseId(),
                            prerequisite.getCode(),
                            prerequisite.getName()
                    )
            );
        }

        Course course = new Course(
                null,
                command.getName(),
                command.getCourseCode(),
                command.getCredits(),
                command.getDescription(),
                List.of()
        );

        Course courseSaved = courseRepository.save(course);

        for (Course prerequisite : prerequisites) {
            prerequisiteRepository.save(
                    courseSaved.getCourseId(),
                    prerequisite.getCourseId()
            );
        }

        return new RegisterCourseResponse(
                true,
                "Se creó la asignatura con éxito",
                new CourseInfo(
                        courseSaved.getCourseId(),
                        courseSaved.getName(),
                        courseSaved.getCode(),
                        courseSaved.getCredits(),
                        courseSaved.getDescription(),
                        prerequisiteInfos
                )
        );
    }
}
