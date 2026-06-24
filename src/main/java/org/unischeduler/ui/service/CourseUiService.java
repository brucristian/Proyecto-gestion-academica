package org.unischeduler.ui.service;

import org.unischeduler.backend.application.service.academic_catalog.in.course.DeleteCourseCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.course.RegisterCourseCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.course.UpdateCourseCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.FindAllPrerequisitesCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.RegisterPrerequisiteCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.dtos.RegisterPrerequisiteResponse;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.DeleteCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.ListAllCoursesUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.RegisterCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.UpdateCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite.FindAllPrerequisitesUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite.RegisterPrerequisiteUseCase;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.mapper.CourseMapper;
import org.unischeduler.ui.viewmodel.course.CourseViewModel;
import org.unischeduler.ui.viewmodel.course.PrerequisiteViewModel;

import java.util.List;

public class CourseUiService {

    private final ListAllCoursesUseCase listAllCoursesUseCase;
    private final RegisterCourseUseCase registerCourseUseCase;
    private final UpdateCourseUseCase updateCourseUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;
    private final RegisterPrerequisiteUseCase registerPrerequisiteUseCase;

    private final CourseMapper mapper;

    public CourseUiService() {

        this.listAllCoursesUseCase = AppContext.getListAllCoursesService();
        this.registerCourseUseCase = AppContext.getRegisterCourseService();
        this.updateCourseUseCase = AppContext.getUpdateCourseService();
        this.deleteCourseUseCase = AppContext.getDeleteCourseService();
        this.registerPrerequisiteUseCase = AppContext.getRegisterPrerequisiteService();

        mapper = new CourseMapper();
    }

    public List<CourseViewModel> loadCourses(){

        return listAllCoursesUseCase
                .execute()
                .getCourses()
                .stream()
                .map(mapper::toViewModel)
                .toList();
    }

    public void registerCourse(RegisterCourseCommand command) {

        registerCourseUseCase.execute(command);

    }

    public void updateCourse(UpdateCourseCommand command) {

        updateCourseUseCase.execute(command);

    }

    public void deleteCourse(String courseId) {

        deleteCourseUseCase.execute(
                new DeleteCourseCommand(courseId)
        );

    }

    public void registerPrerequisite(RegisterPrerequisiteCommand command) {
        registerPrerequisiteUseCase.execute(command);
    }
}
