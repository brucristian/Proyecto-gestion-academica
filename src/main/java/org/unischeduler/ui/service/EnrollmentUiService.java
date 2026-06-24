package org.unischeduler.ui.service;

import org.unischeduler.backend.application.service.enrollment.register.RegisterEnrollmentCommand;
import org.unischeduler.backend.application.service.enrollment.register.dtos.RegisterEnrollmentResponse;
import org.unischeduler.backend.application.service.enrollment.validate.ValidateCreditLimitCommand;
import org.unischeduler.backend.application.service.enrollment.validate.ValidatePrerequisiteCommand;
import org.unischeduler.backend.application.service.enrollment.validate.ValidateScheduleConflictsCommand;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidateCreditLimitResponse;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidatePrerequisiteResponse;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidateScheduleConflictsResponse;
import org.unischeduler.backend.domain.port.in.academic_programming.ListAllGroupsUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.RegisterEnrollmentUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.ValidateCreditLimitUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.ValidatePrerequisiteUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.ValidateScheduleConflictsUseCase;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.mapper.GroupMapper;
import org.unischeduler.ui.viewmodel.group.GroupViewModel;

import java.util.List;

public class EnrollmentUiService {
    private final ListAllGroupsUseCase listAllGroupsUseCase;
    private final ValidateScheduleConflictsUseCase validateScheduleConflictsUseCase;
    private final ValidatePrerequisiteUseCase validatePrerequisiteUseCase;
    private final ValidateCreditLimitUseCase validateCreditLimitUseCase;
    private final RegisterEnrollmentUseCase registerEnrollmentUseCase;


    public EnrollmentUiService() {
        this.listAllGroupsUseCase = AppContext.getListAllGroupsService();
        this.validateScheduleConflictsUseCase = AppContext.getValidateScheduleConflictsService();
        this.validatePrerequisiteUseCase = AppContext.getValidatePrerequisiteService();
        this.validateCreditLimitUseCase = AppContext.getValidateCreditLimitService();
        this.registerEnrollmentUseCase = AppContext.getRegisterEnrollmentService();

    }

    public List<GroupViewModel> listGroups() {
        return listAllGroupsUseCase.execute()
                .getGroups()
                .stream()
                .map(GroupMapper::toViewModel)
                .toList();
    }

    public ValidateScheduleConflictsResponse validateSchedule(ValidateScheduleConflictsCommand command) {
        return validateScheduleConflictsUseCase.execute(command);
    }

    public ValidatePrerequisiteResponse validatePrerequisite(ValidatePrerequisiteCommand command) {
        return validatePrerequisiteUseCase.execute(command);
    }

    public ValidateCreditLimitResponse validateCreditLimit(ValidateCreditLimitCommand command) {
        return validateCreditLimitUseCase.execute(command);
    }

    public RegisterEnrollmentResponse saveEnrollment(RegisterEnrollmentCommand command) {
        return registerEnrollmentUseCase.execute(command);
    }
}
