package org.unischeduler.ui.mapper;

import javafx.collections.FXCollections;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupInfo;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupScheduleInfo;
import org.unischeduler.ui.viewmodel.group.GroupScheduleViewModel;
import org.unischeduler.ui.viewmodel.group.GroupViewModel;

public class GroupMapper {

    public static GroupViewModel toViewModel(GroupInfo groupInfo) {

        GroupViewModel vm =
                new GroupViewModel();

        vm.setId(groupInfo.getGroupId());

        vm.setCourseId(groupInfo.getCourseInfo().getCourseId());

        vm.setCourseName(groupInfo.getCourseInfo().getCourseName());

        vm.setGroupCode(groupInfo.getGroupCode());

        vm.setTeacherId(groupInfo.getTeacherInfo().getTeacherId());

        vm.setTeacherName(groupInfo.getTeacherInfo().getTeacherName());

        vm.setCapacity(groupInfo.getCapacity());

        if (groupInfo.getSchedules() != null) {

            vm.setSchedules(
                    FXCollections.observableArrayList(
                            groupInfo.getSchedules()
                                    .stream()
                                    .map(GroupMapper::toScheduleViewModel)
                                    .toList()
                    )
            );
        }

        return vm;
    }

    private static GroupScheduleViewModel toScheduleViewModel(
            GroupScheduleInfo scheduleInfo
    ) {

        GroupScheduleViewModel vm =
                new GroupScheduleViewModel();

        vm.setId(
                scheduleInfo.getGroupScheduleId()
        );

        vm.setDay(
                scheduleInfo.getDay()
        );

        vm.setStartTime(
                scheduleInfo.getStartTime()
                        .toString()
        );

        vm.setEndTime(
                scheduleInfo.getEndTime()
                        .toString()
        );

        vm.setRoom(
                scheduleInfo.getClassroom()
        );

        return vm;
    }

}