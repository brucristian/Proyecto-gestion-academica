package org.unisheduler.backend.application.service.academic_catalog.out;

import org.unisheduler.backend.application.service.academic_catalog.out.dtos.CourseInfo;
import org.unisheduler.backend.application.service.academic_catalog.out.dtos.ListAllCoursesResponse;
import org.unisheduler.backend.application.service.academic_catalog.out.dtos.PrerequisiteInfo;
import org.unisheduler.backend.domain.model.academic_catalog.entity.Course;
import org.unisheduler.backend.domain.model.academic_catalog.entity.Prerequisite;
import org.unisheduler.backend.domain.port.in.academic_catalog.ListAllCoursesUseCase;
import org.unisheduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unisheduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListAllCoursesServices implements ListAllCoursesUseCase {
    private final CourseRepository courseRepository;
    private final PrerequisiteRepository prerequisiteRepository;

    public ListAllCoursesServices(CourseRepository courseRepository, PrerequisiteRepository prerequisiteRepository) {
        this.courseRepository = courseRepository;
        this.prerequisiteRepository = prerequisiteRepository;
    }

    @Override
    public ListAllCoursesResponse execute() {
        List<Course> courses = courseRepository.findAll();

        List<CourseInfo> courseInfos = new ArrayList<>();
        for(Course course : courses) {
            List<Prerequisite> prerequisites = prerequisiteRepository.findAllPrerequisitesWhereCourseId(course.getCourseId());

            List<PrerequisiteInfo> prerequisiteInfos = new ArrayList<>();
            for(Prerequisite prerequisite : prerequisites) {
                Optional<Course> prerequisiteCourseOptional = courseRepository.findById(prerequisite.getCourseId());
                if(prerequisiteCourseOptional.isEmpty()) {
                    continue;
                }

                Course prerequisiteCourse = prerequisiteCourseOptional.get();
                PrerequisiteInfo prerequisiteInfo = new PrerequisiteInfo(
                        prerequisiteCourse.getCourseId(),
                        prerequisiteCourse.getCode(),
                        prerequisiteCourse.getName()
                );
                
                prerequisiteInfos.add(prerequisiteInfo);
            }
            
            CourseInfo courseInfo = new CourseInfo(
                    course.getCourseId(),
                    course.getName(), 
                    course.getCode(),
                    course.getCredits(),
                    prerequisiteInfos
            );
            
            courseInfos.add(courseInfo);
        }
        
        return new ListAllCoursesResponse(
                true,
                "Se encontraron " + courseInfos.size() + " asignaturas",
                courseInfos
        );
    }
}
