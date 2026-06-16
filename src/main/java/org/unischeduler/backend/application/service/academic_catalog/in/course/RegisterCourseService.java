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

    public RegisterCourseService(CourseRepository courseRepository, PrerequisiteRepository prerequisiteRepository) {
        this.courseRepository = courseRepository;
        this.prerequisiteRepository = prerequisiteRepository;
    }

    @Override
    public RegisterCourseResponse execute(RegisterCourseCommand command) {
        if(courseRepository.existsByCode(command.getCourseCode())) {
            return new RegisterCourseResponse(
                    false,
                    "El codigo de la asignatura ya esta en uso",
                    null
            );
        }


        ArrayList<Course> prerequisites = new ArrayList<>();
        List<PrerequisiteInfo> prerequisiteInfos = new ArrayList<>();
        for(String code : command.getPrerequisiteCourseCodes()) {
            Optional<Course> courseOptional = courseRepository.findByCode(code);
            if(courseOptional.isEmpty()) {
                return new RegisterCourseResponse(
                        false,
                        "El prerrequisito con código " + code + " no existe",
                        null
                );
            }

            Course prerequisite = courseOptional.get();
            prerequisites.add(prerequisite);

            PrerequisiteInfo info = new PrerequisiteInfo(
                    prerequisite.getCourseId(),
                    prerequisite.getCode(),
                    prerequisite.getName()
            );
            prerequisiteInfos.add(info);
        }

        Course course = new Course(
                null,
                command.getName(),
                command.getCourseCode(),
                command.getCredits(),
                command.getDescription(),
                prerequisites
        );

        Course courseSaved = courseRepository.save(course);
        for(Course prerequisite : prerequisites) {
            prerequisiteRepository.save(courseSaved.getCourseId(), prerequisite.getCourseId());
        }

        return new RegisterCourseResponse(
                true,
                "Se creo la asignatura con exito",
                new CourseInfo(
                        courseSaved.getCourseId(),
                        courseSaved.getName(),
                        courseSaved.getCode(),
                        course.getCredits(),
                        courseSaved.getDescription(),
                        prerequisiteInfos
                )
        );
    }
}
