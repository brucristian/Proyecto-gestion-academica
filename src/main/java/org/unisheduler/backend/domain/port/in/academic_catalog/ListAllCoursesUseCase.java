package org.unisheduler.backend.domain.port.in.academic_catalog;

import org.unisheduler.backend.application.service.academic_catalog.out.dtos.ListAllCoursesResponse;

public interface ListAllCoursesUseCase {
    ListAllCoursesResponse execute();
}
