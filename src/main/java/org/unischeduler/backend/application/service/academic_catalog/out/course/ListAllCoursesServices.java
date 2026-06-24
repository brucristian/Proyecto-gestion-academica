package org.unischeduler.backend.application.service.academic_catalog.out.course;
import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.FindAllPrerequisitesCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.dtos.FindAllPrerequisitesResponse;
import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.CourseInfo;
import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.ListAllCoursesResponse;
import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.PrerequisiteInfo;

import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Prerequisite;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.ListAllCoursesUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite.FindAllPrerequisitesUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListAllCoursesServices implements ListAllCoursesUseCase {

    private final CourseRepository courseRepository;
    private final FindAllPrerequisitesUseCase findAllPrerequisitesUseCase;

    public ListAllCoursesServices(
            CourseRepository courseRepository,
            FindAllPrerequisitesUseCase findAllPrerequisitesUseCase) {
        this.courseRepository = courseRepository;
        this.findAllPrerequisitesUseCase =
                findAllPrerequisitesUseCase;
    }

    @Override
    public ListAllCoursesResponse execute() {

        List<Course> courses =
                courseRepository.findAll();

        List<CourseInfo> courseInfos =
                new ArrayList<>();

        for (Course course : courses) {

            FindAllPrerequisitesResponse
                    prerequisitesResponse =
                    findAllPrerequisitesUseCase.execute(
                            new FindAllPrerequisitesCommand(
                                    course.getCourseId()
                            )
                    );

            CourseInfo courseInfo =
                    new CourseInfo(
                            course.getCourseId(),
                            course.getName(),
                            course.getCode(),
                            course.getCredits(),
                            course.getDescription(),
                            prerequisitesResponse
                                    .getPrerequisites()
                    );

            courseInfos.add(courseInfo);
        }

        return new ListAllCoursesResponse(
                true,
                "Se encontraron "
                        + courseInfos.size()
                        + " asignaturas",
                courseInfos
        );
    }
}