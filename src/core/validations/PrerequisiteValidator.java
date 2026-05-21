package core.validations;

import module.academic_catalog.model.Course;
import module.academic_history.model.AcademicHistory;
import module.academic_history.model.CompletedCourse;
import shared.exceptions.PrerequisiteException;

import java.util.List;

public class PrerequisiteValidator {

    public void validate(
            AcademicHistory academicHistory,
            Course course
    ) {

        List<Course> approvedCourses =
                academicHistory.getCompletedCourses()
                        .stream()
                        .filter(CompletedCourse::isApproved)
                        .map(CompletedCourse::getCourse)
                        .toList();

        List<Course> prerequisites =
                course.getPrerequisites();

        boolean approved =
                approvedCourses.containsAll(prerequisites);

        if (!approved) {
            throw new PrerequisiteException(
                    "El estudiante no cumple los prerrequisitos"
            );
        }
    }
}