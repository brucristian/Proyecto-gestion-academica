package org.unischeduler.backend.application.service.academic_catalog.in.course;



import org.unischeduler.backend.application.service.academic_catalog.in.course.dtos.UpdateCourseResponse;
import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.CourseInfo;
import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.PrerequisiteInfo;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.UpdateCourseUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UpdateCourseService implements UpdateCourseUseCase {
    private final CourseRepository courseRepository;
    private final PrerequisiteRepository prerequisiteRepository;

    public UpdateCourseService(CourseRepository courseRepository, PrerequisiteRepository prerequisiteRepository) {
        this.courseRepository = courseRepository;
        this.prerequisiteRepository = prerequisiteRepository;
    }

    @Override
    public UpdateCourseResponse execute(UpdateCourseCommand command) {
        if(!courseRepository.existsByCode(command.getCourseCode())) {
            return new UpdateCourseResponse(
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
                return new UpdateCourseResponse(
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
                command.getCourseId(),
                command.getName(),
                command.getCourseCode(),
                command.getCredits(),
                command.getDescription(),
                prerequisites
        );

        Course courseSaved = courseRepository.update(course);
        for(Course prerequisite : prerequisites) {
            prerequisiteRepository.save(courseSaved.getCourseId(), prerequisite.getCourseId());
        }

        return new UpdateCourseResponse(
                true,
                "Se actualizo la asignatura con exito",
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
