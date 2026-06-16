package org.unischeduler.backend.domain.port.in.academic_catalog.course;


import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.ListAllCoursesResponse;

public interface ListAllCoursesUseCase {
    ListAllCoursesResponse execute();
}
