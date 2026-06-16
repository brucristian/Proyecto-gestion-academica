package org.unischeduler.backend.application.service.academic_programming.out;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.CourseInfo;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupInfo;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupScheduleInfo;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.ListAllGroupsResponse;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.TeacherInfo;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.model.academic_programming.entity.Group;
import org.unischeduler.backend.domain.model.academic_programming.entity.GroupSchedule;
import org.unischeduler.backend.domain.model.academic_programming.entity.Teacher;
import org.unischeduler.backend.domain.port.in.academic_programming.ListAllGroupsUseCase;
import org.unischeduler.backend.domain.port.out.academic_programming.GroupRepository;

import java.util.ArrayList;
import java.util.List;

public class ListAllGroupsServices implements ListAllGroupsUseCase {
    private final GroupRepository groupRepository;

    public ListAllGroupsServices(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public ListAllGroupsResponse execute() {
        List<Group> groups = groupRepository.findAll();

        List<GroupInfo> groupInfos = new ArrayList<>();
        for(Group group : groups) {
            groupInfos.add(toGroupInfo(group));
        }

        return new ListAllGroupsResponse(
                true,
                "Se encontraron " + groupInfos.size() + " grupos",
                groupInfos
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
