package core.validations;

import module.academic_history.model.AcademicHistory;
import module.academic_programming.model.Group;

import java.util.List;

public class EnrollmentValidator {

    private final QuotaValidator quotaValidator;
    private final ScheduleValidator scheduleValidator;
    private final PrerequisiteValidator prerequisiteValidator;

    public EnrollmentValidator(
            QuotaValidator quotaValidator,
            ScheduleValidator scheduleValidator,
            PrerequisiteValidator prerequisiteValidator
    ) {

        this.quotaValidator = quotaValidator;
        this.scheduleValidator = scheduleValidator;
        this.prerequisiteValidator = prerequisiteValidator;
    }

    public void validate(
            AcademicHistory academicHistory,
            Group targetGroup,
            List<Group> currentGroups
    ) {

        quotaValidator.validate(targetGroup);

        scheduleValidator.validate(
                targetGroup,
                currentGroups
        );

        prerequisiteValidator.validate(
                academicHistory,
                targetGroup.getCourse()
        );
    }
}