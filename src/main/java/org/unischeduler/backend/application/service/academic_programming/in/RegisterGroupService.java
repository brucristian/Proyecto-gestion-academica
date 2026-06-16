package org.unischeduler.backend.application.service.academic_programming.in;

import org.unischeduler.backend.application.service.academic_programming.in.dtos.RegisterGroupResponse;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.CourseInfo;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupInfo;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupScheduleInfo;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.TeacherInfo;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.model.academic_programming.entity.Group;
import org.unischeduler.backend.domain.model.academic_programming.entity.GroupSchedule;
import org.unischeduler.backend.domain.model.academic_programming.entity.Teacher;
import org.unischeduler.backend.domain.port.in.academic_programming.RegisterGroupUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.academic_programming.GroupRepository;
import org.unischeduler.backend.domain.port.out.academic_programming.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegisterGroupService implements RegisterGroupUseCase {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public RegisterGroupService(GroupRepository groupRepository, CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public RegisterGroupResponse execute(RegisterGroupCommand command) {
        Optional<Course> courseOptional = courseRepository.findById(command.getCourseId());
        if(courseOptional.isEmpty()) {
            return new RegisterGroupResponse(
                    false,
                    "No existe la asignatura con id " + command.getCourseId(),
                    null
            );
        }

        Optional<Teacher> teacherOptional = teacherRepository.findById(command.getTeacherId());
        if(teacherOptional.isEmpty()) {
            return new RegisterGroupResponse(
                    false,
                    "No existe el profesor con id " + command.getTeacherId(),
                    null
            );
        }

        Group group = new Group(
                null,
                courseOptional.get(),
                command.getGroupCode(),
                teacherOptional.get(),
                command.getCapacity(),
                new ArrayList<>()
        );

        Group groupSaved = groupRepository.save(group);

        return new RegisterGroupResponse(
                true,
                "Se creo el grupo con exito",
                toGroupInfo(groupSaved)
        );
    }

    private GroupInfo toGroupInfo(Group group) {
        Course course = group.getCourse();
        Teacher teacher = group.getTeacher();

        CourseInfo courseInfo = new CourseInfo(
                course.getCourseId(),
                course.getCode(),
                course.getName()
        );

        TeacherInfo teacherInfo = new TeacherInfo(
                teacher.getTeacherId(),
                teacher.getName()
        );

        List<GroupScheduleInfo> scheduleInfos = new ArrayList<>();
        List<GroupSchedule> schedules = group.getSchedules();
        if(schedules != null) {
            for(GroupSchedule schedule : schedules) {
                scheduleInfos.add(new GroupScheduleInfo(
                        schedule.getGroupScheduleId(),
                        schedule.getDayOfWeek().name(),
                        schedule.getStartTime(),
                        schedule.getEndTime(),
                        schedule.getClassroom()
                ));
            }
        }

        return new GroupInfo(
                group.getGroupId(),
                courseInfo,
                group.getGroupCode(),
                teacherInfo,
                group.getCapacity(),
                scheduleInfos
        );
    }
}
