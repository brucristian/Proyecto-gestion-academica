package org.unischeduler.backend.domain.port.in.academic_programming;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.TeacherInfo;

import java.util.List;

public interface ListAllTeachersUseCase {
    List<TeacherInfo> execute();
}
