package org.unischeduler.backend.infrastructure.out.mapper.academic_history;

import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.model.academic_history.entity.AcademicHistory;
import org.unischeduler.backend.domain.model.academic_history.enums.AcademicHistoryCourseStatus;
import org.unischeduler.backend.domain.model.enrollment.entity.Student;
import org.unischeduler.backend.infrastructure.out.entity.academic_history.AcademicHistoryEntity;

import java.util.ArrayList;
import java.util.List;

public class AcademicHistoryMapper {

    private AcademicHistoryMapper() {
    }

    public static AcademicHistoryEntity toEntity(
            AcademicHistory history
    ) {

        List<String> completedCoursesIds =
                history.getCompletedCourses()
                        .stream()
                        .map(Course::getCourseId)
                        .toList();

        return new AcademicHistoryEntity(
                String.valueOf(history.getHistoryId()),
                history.getStudent().getStudentId(),
                completedCoursesIds,
                history.getGrade(),
                history.getStatus().name()
        );
    }

    public static AcademicHistory toDomain(
            AcademicHistoryEntity entity,
            Student student,
            List<Course> completedCourses
    ) {

        return new AcademicHistory(
                entity.getHistoryId(),
                student,
                new ArrayList<>(completedCourses),
                entity.getGrade(),
                AcademicHistoryCourseStatus.valueOf(
                        entity.getStatus()
                )
        );
    }
}