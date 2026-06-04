package org.unischeduler.backend.domain.port.in.academic_catalog.course;


import org.unischeduler.backend.application.service.academic_catalog.in.course.UpdateCourseCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.course.dtos.UpdateCourseResponse;

public interface UpdateCourseUseCase {
    UpdateCourseResponse execute(UpdateCourseCommand command);
}
