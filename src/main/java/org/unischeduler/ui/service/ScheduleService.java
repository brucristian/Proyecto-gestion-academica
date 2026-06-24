package org.unischeduler.ui.service;

import org.unischeduler.backend.application.service.academic_programming.out.GetScheduleCommand;
import org.unischeduler.backend.application.service.academic_programming.out.dtos.ScheduleInfo;
import org.unischeduler.backend.domain.port.in.academic_programming.schedule.GetScheduleUseCase;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.viewmodel.schedule.ScheduleItem;

import java.util.List;

public class ScheduleService {
    private final GetScheduleUseCase getScheduleUseCase;

    public ScheduleService() {
        this.getScheduleUseCase = AppContext.getGetScheduleService();
    }

    public List<ScheduleItem> getSchedule(GetScheduleCommand command) {
        return getScheduleUseCase.execute(command).getSchedules()
                .stream()
                .map(ScheduleItem::toScheduleItem)
                .toList();
    }
}
