package org.unischeduler.ui.mapper;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.GroupScheduleInfo;
import org.unischeduler.ui.viewmodel.group.GroupScheduleViewModel;

import java.time.LocalTime;

public final class GroupScheduleMapper {

    public static GroupScheduleInfo toInfo(GroupScheduleViewModel viewModel, String groupId, String courseName) {
        return new GroupScheduleInfo(
                groupId,
                courseName,
                viewModel.getId(),
                viewModel.getDay(),
                LocalTime.parse(viewModel.getStartTime()),
                LocalTime.parse(viewModel.getEndTime()),
                viewModel.getRoom()
        );
    }
}
