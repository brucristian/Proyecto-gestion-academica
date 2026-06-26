package org.unischeduler.backend.application.service.academic_programming.out;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.TeacherInfo;
import org.unischeduler.backend.domain.port.in.academic_programming.ListAllTeachersUseCase;
import org.unischeduler.backend.domain.port.out.academic_programming.TeacherRepository;

import java.util.List;

public class ListAllTeachersService implements ListAllTeachersUseCase {
    private final TeacherRepository teacherRepository;

    public ListAllTeachersService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @Override
    public List<TeacherInfo> execute() {
        return teacherRepository.findAll()
                .stream()
                .map(teacher -> new TeacherInfo(
                        teacher.getTeacherId(),
                        teacher.getName()
                ))
                .toList();
    }
}
