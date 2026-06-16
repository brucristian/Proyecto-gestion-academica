package org.unischeduler.backend.domain.port.in.academic_catalog.course;


import org.unischeduler.backend.application.service.academic_catalog.in.course.RegisterCourseCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.course.dtos.RegisterCourseResponse;

public interface RegisterCourseUseCase {
    RegisterCourseResponse execute(RegisterCourseCommand command);
}
