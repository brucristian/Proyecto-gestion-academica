package org.unischeduler.ui.viewmodel.schedule;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.ScheduleInfo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public record ScheduleItem(
        String courseName,
        String room,
        DayOfWeek day,
        LocalTime start,
        LocalTime end
) {

    public static ScheduleItem toScheduleItem(ScheduleInfo info) {

        return new ScheduleItem(
                info.getCourseName(),
                info.getRoom(),
                DayOfWeek.valueOf(info.getDay()),
                info.getStart(),
                info.getEnd()
        );
    }

    public List<Integer> hoursUsed() {

        List<Integer> result =
                new ArrayList<>();

        for (
                int hour = start.getHour();
                hour < end.getHour();
                hour++
        ) {
            result.add(hour);
        }

        return result;
    }
}
