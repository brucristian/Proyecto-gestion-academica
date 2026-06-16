package org.unischeduler.backend.domain.port.in.academic_programming;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.ListAllGroupsResponse;

public interface ListAllGroupsUseCase {
    ListAllGroupsResponse execute();
}
