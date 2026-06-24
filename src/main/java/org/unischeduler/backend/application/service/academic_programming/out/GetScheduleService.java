package org.unischeduler.backend.application.service.academic_programming.out;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.GetScheduleResponse;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.ScheduleInfo;
import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.backend.domain.model.academic_programming.entity.GroupSchedule;
import org.unischeduler.backend.domain.model.enrollment.entity.Enrollment;
import org.unischeduler.backend.domain.port.in.academic_programming.schedule.GetScheduleUseCase;
import org.unischeduler.backend.domain.port.out.academic_programming.GroupScheduleRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetScheduleService implements GetScheduleUseCase {
    private final EnrollmentRepository enrollmentRepository;
    private final GroupScheduleRepository groupScheduleRepository;

    public GetScheduleService(EnrollmentRepository enrollmentRepository, GroupScheduleRepository groupScheduleRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.groupScheduleRepository = groupScheduleRepository;
    }

    @Override
    public GetScheduleResponse execute(GetScheduleCommand command) {

        Enrollment enrollment =
                enrollmentRepository.findByStudentAndActivePeriod(command.getStudentId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "El estudiante no tiene una matricula en este periodo academico"
                        ));

        Map<String, String> courseNameByGroupId = enrollment.getDetails().stream()
                .collect(Collectors.toMap(
                        d -> d.getGroup().getGroupId(),
                        d -> d.getGroup().getCourse().getName()
                ));

        List<GroupSchedule> schedules = enrollment.getDetails().stream()
                .map(d -> groupScheduleRepository.findAllWhereGroupId(
                        d.getGroup().getGroupId()
                ))
                .flatMap(List::stream)
                .toList();

        List<ScheduleInfo> scheduleInfos = schedules.stream()
                .map(s -> ScheduleInfo.toScheduleInfo(
                        s,
                        courseNameByGroupId.get(s.getGroupId())
                ))
                .toList();

        return new GetScheduleResponse(
                true,
                "Horario retornado con exito",
                scheduleInfos
        );
    }
}
