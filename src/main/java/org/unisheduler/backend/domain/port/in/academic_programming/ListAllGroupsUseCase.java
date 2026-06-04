package org.unisheduler.backend.domain.port.in.academic_programming;

import org.unisheduler.backend.application.service.academic_programming.out.dtos.ListAllGroupsResponse;

public interface ListAllGroupsUseCase {
    ListAllGroupsResponse execute();
}
